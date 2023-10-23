package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.domain.consulta.AgendaDeConsulta;
import med.voll.api.domain.consulta.DadosDetalhamentoConsulta;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("consultas")
@SecurityRequirement(name = "bearer-kay") // anotacao de restricao do sprink doc, aqui tds sao restritos
public class ConsultaController {

    private AgendaDeConsulta agenda;

    @PostMapping
        @Transactional
        public ResponseEntity agendar(@RequestBody @Valid DadosAgendamentoConsulta dados) {
          agenda.agendar(dados);
            return ResponseEntity.ok(new DadosDetalhamentoConsulta(null, null, null, null));
        }

    @DeleteMapping
    @Transactional
    public ResponseEntity cancelar(@RequestBody @Valid DadosCancelamentoConsulta dados) {
        agenda.cancelar(dados);
        return ResponseEntity.noContent().build();
    }


}

