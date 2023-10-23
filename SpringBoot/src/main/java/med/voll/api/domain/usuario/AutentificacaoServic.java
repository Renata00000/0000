package med.voll.api.domain.usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AutentificacaoServic implements UserDatailsServic{

    @Autowired
    private UsuarioRepository repository;

    @Override
                 //loadUserName sera o metodo que o spring vai chamar todoa vez que o usuario fizer login busca essa classe  e aplica esse metodo
    public UserDatails loadUsername(String username ) throws UsernameNotFoundException{
        return repository.findByLogin(username);
        }
}
