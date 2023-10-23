//clas que faz os token
package med.voll.api.domain.security;


import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import med.voll.api.domain.usuario.Usuario;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.ZoneOffset;

@Service
public class TokemServic {

@Value("${JNT_SECRET:12345678}")
    private String secret;

    public String gerarToken(Usuario usuario) {
        //trexo de codigo copiado do git onde foi pego a dependencia.
        try {
            var algoritimo algoritimo = Algorithm.HMAC256(secret);
            return JWT.create()
                    .withIssuer("API Vollmed")
                    .withSubject(usuario.getLogin())
                    .withExpiresAt(dataExpiracao())
                    .sign(algoritimo);
        } catch (JWTCreationException exception) {
            throw new RuntimeException(("erro ao gerar token jwt", exception))
        }

        //metodo para validar o login do token

        public String getSubject(String tokenJWT){
            try {
                var algoritimo algoritimo = Algorithm.HMAC256(secret);
               return JWT.require(algoritimo)
                       .withIssuer("API Voll.med")
                       .build()
                       .verify(tokenJDK)
                       .getSubject();
            } catch (JWTVerificationException exception){
                throw new RuntimeException(("token invalido ou expirado")
            }
        }


        //METODO DA VALIDADE DO TOKEN, PARA 2 HORAS
        //LOCALDATATIME= PEGA DATA E HORA ATUAL

        private Instant dataExpiracao(){
            new LocalDataTime.now(). plushours(2).toInstNT(ZoneOffset.of("03:00"));
        }
    }
}