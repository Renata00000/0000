package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.medico.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

// com os (@) a spring sabe que essa e uma class controller e ela esta mapeando minha url medicos que fiz na api
@RestController
@RequestMapping("medicos")
public class MedicoController {

    @Autowired
    private MedicoRepository repository;

    //    estou dizendo/ se chegar uma requisicao do tipo poust para barra medico, execute o metodo a baixo @ poust foi
//    como eu defini a url da api quando criei

//    no parametro requesti bdy json p ele ir la na minha requisao de api e pegar meu json que
//    coloquei no body da requisicap
    @PostMapping
    @Transactional                    //@valid para validar as ainformacoes que passei na class dados cadastrais
    public void cadastrar(@RequestBody  @Valid DadosdeCadastroMedico dados){
repository.save( new Medico(dados));
    }


    // n precisa colocar  @transoctionol e apenas um metodo de retorno, n esta fazendo nenhum tipo de
    @GetMapping                                //page para paginar a paginas
    public Page<DadosListagemMeedicos> listar( @PageableDefault(size = 10, sort = {"nome"})Pageable paginacao){
        return repository.findAllByAtivoTrue( paginacao).map(DadosListagemMeedicos::new);
    }
    @PutMapping
    @Transactional
    public void atualizar(@RequestBody  @Valid DadosAtualizacaoMedico dados){
        var medico  = repository.getReferenceById(dados.id());
        medico.atualizarInformacoes(dados);
    }

    //dizendo p deletar po id que vou colocar no insomnia
    @DeleteMapping("/{id}")
    @Transactional
    public void excluir(@PathVariable long id){
        var medico  = repository.getReferenceById(id);
        medico.excluie();

    }

}
