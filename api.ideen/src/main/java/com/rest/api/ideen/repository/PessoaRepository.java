package com.rest.api.ideen.repository;

import com.rest.api.ideen.entity.Pessoas;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PessoaRepository extends JpaRepository<Pessoas, Long> {


    Optional<Pessoas> findByCpf(String cpf);
}