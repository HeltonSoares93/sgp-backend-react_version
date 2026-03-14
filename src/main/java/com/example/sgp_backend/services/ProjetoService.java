package com.example.sgp_backend.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.sgp_backend.models.Projeto;
import com.example.sgp_backend.repositories.ProjetoRepository;

@Service
public class ProjetoService {

  @Autowired
  private ProjetoRepository repository;

  public List<Projeto> findAllProjetos() {
    return repository.findAll();
  }

  public Optional<Projeto> findProjetoPorId(Long id) {
    return repository.findById(id);
  }

  public Projeto salvarProjeto(Projeto u) {
    return repository.save(u);
  }


  public void deletarProjeto(Long id) {
    repository.deleteById(id);
  }

}
