package med.voll.api.domain.consulta;


import med.voll.api.controller.DadosCancelamentoConsulta;
import med.voll.api.domain.ValidacaoException;
import med.voll.api.domain.consulta.validacoes.ValidadorDeAgendamentoDeConsulta;
import med.voll.api.domain.medico.Medico;
import med.voll.api.domain.medico.MedicoRepository;
import med.voll.api.domain.paciente.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service //uma class de servicos, no nosso caso regra de negocios
public class AgendaDeConsulta {

    @Autowired
    private ConsultaRepository consultaRepository;

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

    //esse metodo pega a lista de validadores que tenho e unifiquei en interface, pois todos tem a mesma assinatura, entao joga aqui
    @Autowired private List<ValidadorDeAgendamentoDeConsulta> validadores;

    public void agendar(DadosDetalhamentoConsulta dados){
        //VERIFICANDO SE EXSTE ID DO PACIWNTE, se paciente for diferente
        if (!pacienteRepository.existsById(dados.idPaciente())){
            throw new ValidacaoException("id de paciente nao existe");
        }
        //id medico opcional, verifico se esta nulo caso nao, ai eu verifico se existe
        if (dados.idMedico() != null && !medicoRepository.existsById(dados.idMedico())){
            throw new ValidacaoException("id de Medico nao existe");
        }
        //percorre a lista de validadores e injeta od dados
        validadores.forEach(v -> v.validar((dados)));

        var paciente = pacienteRepository.findById(dados.idPaciente()).get();
        var medico = escolherMedico(dados);
        var consulta = new Consultas.Consulta(null, medico, paciente, dados.data());
        consultaRepository.save(consulta);

    }
//metodo de consulta de medicos aleatoria
    private Medico escolherMedico(DadosDetalhamentoConsulta dados) {
        if (dados.idMedico() != null){
            return medicoRepository.getReferenceById(dados.idMedico());
        }
        if (dados.especialidade() ==null){
            throw new ValidacaoException("especialidade  e obrigatorio quando  o medico nao escolhido");
            }
            return medicoRepository.escolherMedicoAleatorioLivreNaData(dados.especialidade(),dados.data());

        }


    public void cancelar(DadosCancelamentoConsulta dados) {
        if (!consultaRepository.existsById(dados.idConsulta())) {
            throw new ValidacaoException("Id da consulta informado não existe!");
        }

        var consulta = consultaRepository.getReferenceById(dados.idConsulta());
        consulta.cancelar(dados.motivo());
    }
    }

