package com.rest.api.ideen.execption;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class TratandoErros {


        @ExceptionHandler(EntityNotFoundException.class)
        public ResponseEntity tratarErro404() {
            return ResponseEntity.notFound().build();
        }

        @ExceptionHandler(MethodArgumentNotValidException.class)
        public ResponseEntity tratarErro400(MethodArgumentNotValidException ex) {
            var erros = ex.getFieldErrors();
            return ResponseEntity.badRequest().body(erros.stream().map(DadosErroValidacao::new).toList());
        }

        //DTO
    private record DadosErroValidacao(String campo, String mesagem){
            public  DadosErroValidacao(FieldError erro){
                this(erro.getField(), erro.getDefaultMessage());
            }                       

    }

       


