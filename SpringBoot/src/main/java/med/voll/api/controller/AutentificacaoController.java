package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.controller.domain.usuarios.DadosAutentificacao;
import med.voll.api.domain.security.TokemServic;
import med.voll.api.domein.usuario.DadosAutentificacao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.naming.AuthenticationException;


// class metodo de autentificacao de token
@RestController
@RequestMapping("/login")
public class AutentificacaoController {
    @Autowired
    private AuthenticationManager maneger;

    @Autowired
    private TokemServic tokemServic

    @PostMapping
    public ResponseEntity efetuarLogin(@RequestBody @Valid DadosAutentificacao dados){
        var authenticationToken =new UsernamePasswordAuthenticationToken(dados.login(), dados.senha())
var autentication = maneger.authenticate(authenticationToken);
        var tokenJDT =tokemServic.gerarToken((autentication.getPrincipal()))
        return ResponseEntity.ok(new DadosTokenjWT);
    }
}
