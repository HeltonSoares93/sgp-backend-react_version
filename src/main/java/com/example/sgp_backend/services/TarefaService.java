package com.example.sgp_backend.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.sgp_backend.models.Tarefa;
import com.example.sgp_backend.repositories.TarefaRepository;

@Service
public class TarefaService {

  @Autowired
  private TarefaRepository repository;

  public List<Tarefa> findAllTarefas() {
    return repository.findAll();
  }

  public Optional<Tarefa> findTarefaPorId(Long id) {
    return repository.findById(id);
  }

  public Tarefa salvarTarefa(Tarefa u) {
    return repository.save(u);
  }


  public void deletarTarefa(Long id) {
    repository.deleteById(id);
  }

}
