# CRUD Java Spring Boot

Este projeto é um CRUD (Create, Read, Update, Delete) de pessoas, desenvolvido em Java com Spring Boot. Ele inclui todas as requisições HTTP necessárias, mensagens de retorno e tratamentos de erro.

## Funcionalidades

- **Create (Post)**
  - Grava CPF
  - Valida se o CPF tem 14 dígitos, lança Exception caso tenha mais ou menos
  - Valida se o número do CPF já existe na base de dados ao realizar novo cadastro
  - Adiciona campo e-mail e valida se o formato de e-mail é válido
  - Adiciona campo data de nascimento e valida se a data de nascimento é superior a 150 anos
  - Retorna erro se algum atributo obrigatório estiver em branco ou nulo

- **Read (Get)**
  - Cria uma API Get por ID e retorna 404 (Not Found) caso o registro não seja encontrado
  - Implementa paginação e ordenação nos resultados das consultas
  - Retorna erro de 404 (Not Found) caso não encontre registros
  - Trata erros de ID não localizado

- **Update (Put)**
  - Atualiza apenas atributos desejados por registro (sem necessidade de atualizar todo objeto)
  - Retorna o registro completo com a alteração
  - Retorna erro se o ID do registro não for encontrado

- **Delete (Delete)**
  - Deleta registros usando ID
  - Retorna erro de 404 (Not Found) caso o registro não seja encontrado
  - Retorna erro se o cadastro não for encontrado na base de dados

## Validações

- **CPF**
  - Valida se o CPF possui 14 dígitos
  - Verifica se o CPF já existe no banco de dados ao realizar um novo cadastro

- **E-mail**
  - Valida se o formato de e-mail é válido

- **Data de Nascimento**
  - Valida se a data de nascimento não é superior a 150 anos

## Tratamento de Erros

- **Erro 404 (Not Found)**
  - Retorna quando um registro não é encontrado nas requisições GET por ID
  - Retorna quando tenta deletar um registro que não existe

- **Erro 500 (Internal Server Error)**
  - Retorna para erros inesperados do servidor

- **Mensagens de Erro Personalizadas**
  - CPF inválido (maior ou menor que 14 dígitos)
  - CPF já existente no banco de dados
  - Atributos obrigatórios em branco ou nulo
  - Data de nascimento superior a 150 anos
  - E-mail inválido

## Estrutura do Projeto

- **Controladores (Controllers)**
  - Implementam as requisições HTTP (GET, POST, PUT, DELETE)

- **Serviços (Services)**
  - Contêm a lógica de negócios

- **Repositórios (Repositories)**
  - Interagem com o banco de dados

- **Entidades (Entities)**
  - Representam as tabelas do banco de dados

- **DTOs (Data Transfer Objects)**
  - Utilizados para transferência de dados entre camadas

- **Utilitários (Utils)**
  - Funções auxiliares como a validação de CPF

- **Tratamento de Erros (Exception Handling)**
  - Classes específicas para captura e tratamento de exceções

## Tecnologias Utilizadas
- Java
- Spring Boot
- Maven
- H2 Database (ou qualquer outro banco de dados de sua escolha)
- Hibernate

