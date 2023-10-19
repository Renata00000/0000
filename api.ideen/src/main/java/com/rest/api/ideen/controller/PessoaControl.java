package com.rest.api.ideen.controller;

import com.rest.api.ideen.repository.PessoaRepository;
import com.rest.api.ideen.entity.Pessoas;
import com.rest.api.ideen.util.ValidaCpf;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.Optional;

@RestController
@RequestMapping("/pessoas")
@AllArgsConstructor
public class PessoaControl {

    PessoaRepository repository;



    @GetMapping
    public ResponseEntity<?> getAllPessoa(Pageable page) {
        Page<Pessoas> pessoas = repository.findAll(page);
        if (pessoas.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No records registered");
        }
        return ResponseEntity.ok(pessoas);


    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getPessoasById(@PathVariable Long id){
         Optional<Pessoas> pessoa = repository.findById( id);
         if (pessoa.isPresent()){
             return ResponseEntity.ok().body(pessoa.get());
         }else{
             return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Record not found.");


         }

    }


    @PostMapping
    public ResponseEntity<String> savePessoas(@RequestBody @Valid Pessoas pessoa) {
        Optional<Pessoas> person = repository.findByCpf(pessoa.getCpf());
        if (person.isPresent()) {
            return ResponseEntity.badRequest().body("CPF already exist!");
        } else {
            if(ValidaCpf.validarCPF(pessoa.getCpf())) {
                this.repository.save(pessoa);
                return ResponseEntity.ok().body("New Record saved");
            }
            return ResponseEntity.badRequest().body("Invalid CPF!");
        }
    }


    @PutMapping("/{id}")
    public ResponseEntity<?> atualizaPessoas(@PathVariable Long id, @RequestBody Pessoas pessoas) {
        Optional<Pessoas> optionalPessoa = repository.findById(id);

        if (optionalPessoa.isPresent()) {
            Pessoas p = optionalPessoa.get();

            if (pessoas.getNome() != null) {
                p.setNome(pessoas.getNome());
            }
            if (pessoas.getIdade() != 0) {
                p.setIdade(pessoas.getIdade());
            }
            if (pessoas.getCpf() != null) {
                p.setCpf(pessoas.getCpf());
            }
            if (pessoas.getNacionalidade() != null) {
                p.setNacionalidade(pessoas.getNacionalidade());
            }

            Pessoas updatedPessoa = repository.save(p);

            return ResponseEntity.ok(updatedPessoa);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(" Record not found.");

        }
    }




    @DeleteMapping("/{id}")
public ResponseEntity<String> deletaPessoas(@PathVariable Long id) {
    Optional<Pessoas> pessoa = repository.findById(id);
    if (pessoa.isPresent()) {
        repository.deleteById(id);
        return ResponseEntity.ok("Record deleted!");
    } else {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Record not found");

    }

    }
  }









