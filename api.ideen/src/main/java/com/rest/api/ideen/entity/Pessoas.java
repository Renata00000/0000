    package com.rest.api.ideen.entity;


    import com.fasterxml.jackson.annotation.JsonFormat;
    import jakarta.persistence.*;
    import jakarta.validation.constraints.Email;
    import jakarta.validation.constraints.NotBlank;
    import jakarta.validation.constraints.NotNull;
    import jakarta.validation.constraints.Past;
    import lombok.AllArgsConstructor;
    import lombok.Data;
    import lombok.NoArgsConstructor;

    import java.time.LocalDate;



    @Entity
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public class Pessoas {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        @NotBlank
        @NotNull
        private String nome;
        @NotBlank
        private String cpf;
        @Past
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
        private LocalDate dataDeNascimento;
        @Email
        private String email;
        @NotBlank
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
        public void setCpf(String cpf) {
            this.cpf = cpf;
        }

        public LocalDate getDataDeNascimento() {
            return dataDeNascimento;
        }

        public void setDataDeNascimento(LocalDate dataDeNascimento) {
            this.dataDeNascimento = dataDeNascimento;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getNacionalidade() {
            return nacionalidade;
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


