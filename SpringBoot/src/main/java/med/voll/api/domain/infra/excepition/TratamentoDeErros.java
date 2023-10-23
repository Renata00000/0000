
//class criada para fazer tratamento de erros, para n ter que ficar colocando em todas as requisicoes http, padronizando para todas as requisicoes
package med.voll.api.domain.infra.excepition;


import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice //estou dizendo que essa e uma class do sprin especifica para tratar erros
public class TratamentoDeErros {


    // spring sabe se em qualquer controller for lancada uma excepition EntityNotfondExcepition, ele executa a linha 16 no get
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity tratarErro404(){
        return ResponseEntity.notFound().build();
    }

    //essa exeption me devolve o numero de erros junto de seus campos, no post
@ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity tratarErro400(MethodArgumentNotValidException ex){
        var erros = ex.getFieldError(); //getfielderror  sig lista de rros, ele me retorna nao apenas qual o erro mas os campos de rro
//                         convertendo class record erros me de um strean e mapei os objetos fid erros e para ojt dado validacao
    return ResponseEntity.badRequest().body(erros.stream().map(DadosErroValidacao ::new).toList());
    }

//    criando class record dentro aqui mesmo, pois a class so sera isso mesmo sem a necessidade de criar uma outra
private record DadosErroValidacao(String campo, String mensagem ){
        public DadosErroValidacao(FieldError erro) {
            this(erro.getField()), erro.getDefaultMessage());
        }
    }
}


