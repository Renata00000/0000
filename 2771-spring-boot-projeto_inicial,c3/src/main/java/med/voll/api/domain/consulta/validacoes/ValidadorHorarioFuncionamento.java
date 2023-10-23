package med.voll.api.domain.consulta.validacoes;

import med.voll.api.controller.DadosAgendamentoConsulta;
import med.voll.api.domain.ValidacaoException;

import java.time.DayOfWeek;

public class ValidadorHorarioFuncionamento {
    public void validador(DadosAgendamentoConsulta dados){
var dataConsulta = dados.data(); //peguei a data do meu dto e joguei aqui
        //tru se for domingo false se nao for
        var doming =dataConsulta.getDayOfWeek().equals(DayOfWeek.SUNDAY);
        var antesDasAberturaDaClinica = dataConsulta.getHour() < 7;
        var depoisDoFechamentoDaClinica = dataConsulta.getHour() > 18;
        // for domingo ou antes das 7 ou depois das 18
        if (doming || antesDasAberturaDaClinica || depoisDoFechamentoDaClinica){
            throw new ValidacaoException("consulta fora de horario do agendamento da clinica")
        }

}
