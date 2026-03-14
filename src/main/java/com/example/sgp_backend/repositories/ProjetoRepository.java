package com.example.sgp_backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.sgp_backend.models.Projeto;

@Repository
public interface ProjetoRepository extends JpaRepository<Projeto, Long> {

}
