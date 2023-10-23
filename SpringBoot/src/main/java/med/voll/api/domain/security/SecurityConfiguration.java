// class que configura logn e senha, para nao precisar ficar usando a senha fornecida pelo spring security que vem na linha de comando

package med.voll.api.domain.security;

import jakarta.websocket.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.HttpSecurityDsl;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configurable   //indicando que e uma class de configuracao
@EnableWebFluxSecurity  //indicando que as personalizacao de seguranca
public class SecurityConfiguration {
    @Autowired
    SecurityFilter securityFilter;


//    @Bean   //para devolver um obj para o string                                                                 //csrf se der alguma exception ai lanca
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) thorows Exception{
//        //csrf desabilita a protecao contra ataque csrf   mas como estamos usando token o proprio tokem faz isso
//        return http.csrf().disable()
//                .sessionManegament().sessionCreationPolicy.(SessionCreationPolicy.STATELESS)
//                .and().autorizaRequest; metodo para configurar nossas requisicoes
    // dizendo que tod os campos de login sera liberado, mas barra todo o resto
//    .antMatchers(HttpMethod.POST, "/login").permitAll()
//        qualquer outra requisicao vc verifica
//    .anyRequesty().authentication
    // dizer qual filtro ele chama promeiro se e o meu ou do spring, primeiro meu filtro securityfilter depois o seu, username
    .and().assFilterBifore(SecurityFilter, UsernamePasswordAuthenticationFilter.class);
//          .and().build()

//    metodo a cima esta desatualizado por conta da versao

        @Bean
        public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
            return http.csrf(csrf -> csrf.disable())
                    .sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                    .build();
        }

            @Bean
            public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration){
            return configuration.getAuthenticationManager();




            //metodo que cinaliza para que a senha passada na linha de comando esta quiptografada
            public PasswordEncoder  PasswordEncoder(){
                return new  BCrryptPasswordEncoder();
                }
            }
    }

