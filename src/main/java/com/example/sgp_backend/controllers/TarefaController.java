package com.example.sgp_backend.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.sgp_backend.models.Tarefa;
import com.example.sgp_backend.services.TarefaService;

@RestController
@RequestMapping("/tarefas")
public class TarefaController {

  @Autowired
  private TarefaService service;

  // Salvar Tarefa
  @PostMapping
  public ResponseEntity<Tarefa> cadastrarTarefa(@RequestBody Tarefa u) {
    return ResponseEntity.status(HttpStatus.CREATED).body(service.salvarTarefa(u));
  }

  // listar todos os Tarefas
  @GetMapping
  public ResponseEntity<List<Tarefa>> findAllTarefas() {
    return ResponseEntity.ok().body(service.findAllTarefas());
  }

  // buscar Tarefa por ID
  @GetMapping(value = "/{id}")
  public ResponseEntity<Optional<Tarefa>> buscarTarefaPorId(@PathVariable("id") Long id) {
    Optional<Tarefa> tarefaExiste = service.findTarefaPorId(id);

    if (tarefaExiste.isEmpty()) {
      return ResponseEntity.notFound().build();
    }

    return ResponseEntity.ok().body(service.findTarefaPorId(id));
  }

  // Atualizar Tarefa
  @PutMapping("/{id}")
  public ResponseEntity<Tarefa> atualizarTarefa(@PathVariable Long id, @RequestBody Tarefa u) {
    Optional<Tarefa> tarefaExiste = service.findTarefaPorId(id);
    if (tarefaExiste.isEmpty()) {
      return ResponseEntity.notFound().build();
    }
    u.setId(id);
    return ResponseEntity.ok().body(service.salvarTarefa(u));
  }

  // deletar Tarefa por ID
  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deletarTarefaId(@PathVariable Long id) {
    Optional<Tarefa> tarefaExiste = service.findTarefaPorId(id);
    if (tarefaExiste.isEmpty()) {
      return ResponseEntity.notFound().build();
    }
    service.deletarTarefa(id);
    return ResponseEntity.noContent().build();
  }

}
