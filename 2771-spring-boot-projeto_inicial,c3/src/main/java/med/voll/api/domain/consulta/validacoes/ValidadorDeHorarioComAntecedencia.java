package med.voll.api.domain.consulta.validacoes;

import med.voll.api.controller.DadosAgendamentoConsulta;
import med.voll.api.domain.ValidacaoException;

import java.time.Duration;
import java.time.LocalDateTime;

public class ValidadorDeHorarioComAntecedencia {


    public void validador(DadosAgendamentoConsulta dados) {
        var dataConsulta = dados.data();
        var agora = LocalDateTime.now();  //pegando horario de agora
        var diferencaEmMinutos = Duration.between(agora,dataConsulta).toMinutes();  // to que pega a duracao em minutos
        if (diferencaEmMinutos < 30){
            throw new ValidacaoException("consulta deve ser marcada com no min 30 min de antecedencia")
        }
    }
}