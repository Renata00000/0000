package med.voll.api.domain.security;


import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import med.voll.api.domain.usuario.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component // quando vc quer carregar uma class generica sem especificacao, ela n tem um tipo especifico, n e nenhuma, cless interface

public class SecurityFilter  extends OncePerRequestFilter {

    //importando class que esta com o metodo de validar o filtro do tokewm
    @Autowired
    private TokemServic tokenService;

    //usando usuarioo do banco de dados
    @Autowired
    private UsuarioRepository repositori;

    // metodo filter que o sring vai chamar toda vez que for feita uma requisicao hht para validar ou n o login
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        var tokenJWT = recuperarTokeb(request);
//    jogando metodo de validacao de token aqui
        if (tokenJWT != null) {
            var subject = tokenService.getSubject(tokenJWT);
            va usuario =repositori.findByLogin(subject);

            var autentication = new UsernamePasswordAuthenticationToken();
            SecurityContextHolder.getContext().setAuthentication(autentication);
        }
        // chamando o proximo filtro
        filterChain.doFilter(request, response);
    }

    private String recuperarTokeb(HttpServletRequest request) {
        var autorizationHeder = request.getHeader("Autorization");
        if (autorizationHeder != null) {
            return autorizationHeder.replace("Bearer", "");
        }
        return null;
    }
}


// if (autorizationHeder == null){
//         //se der null crio uma exception, se nao retorno que deu certo
//         throw new RuntimeException("token jwt nao enviado no cadastro")
//         }
//         //.replace para subistituir a palavra Bearer que ven junto na linha de comando por nada, aspas vazia
//         return autorizationHeder.replace("Bearer","") ;
//         }