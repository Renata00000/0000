package med.voll.api.domain.consulta;

import java.time.LocalDateTime
public class DadosDetalhamentoConsulta {
    public Long idPaciente() {
        return null;
    }

    public Long idMedico() {
        return null;
    }

    public boolean especialidade() {
    }

    public Object data() {
    }

    private record DadosDetalhamentoConsulta(Long id, Long idMedico, Long IdPaciente, LocalDateTime data) {
    }
}
