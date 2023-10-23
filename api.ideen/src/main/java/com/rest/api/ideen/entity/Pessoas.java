package com.rest.api.ideen.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Pessoas {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String cpf;
    @Past
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate dataDeNascimento;
    @Email
    private String email;
    private String nacionalidade;

    public Long getId() {
        return id;
    }
    public String getNome() {
       return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getCpf() {
        return cpf;
    }
    public LocalDate getDataDeNascimento() {
            return dataDeNascimento;
    }

    public void setDataDeNascimento(LocalDate dataDeNascimento) {
            this.dataDeNascimento = dataDeNascimento;
    }
    public void setCpf(String cpf) {
       this.cpf = cpf;
    }
    public String getNacionalidade() {
        return nacionalidade;
    }
     public String getEmail() {
            return email;
    }

     public void setEmail(String email) {
            this.email = email;
    }
    public void setNacionalidade(String nacionalidade) {
        this.nacionalidade = nacionalidade;
    }

      public boolean validaIdadeMaxima() {
            LocalDate hoje = LocalDate.now();
            int idadeMaxima = 150;
            return dataDeNascimento != null && dataDeNascimento.plusYears(idadeMaxima).isAfter(hoje);
        }
}
