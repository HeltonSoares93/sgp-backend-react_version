package com.example.sgp_backend.controllers;

import java.util.List;
import java.util.Objects;
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

import com.example.sgp_backend.models.Projeto;
import com.example.sgp_backend.services.ProjetoService;

@RestController
@RequestMapping("/Projetos")
public class ProjetoController {

  @Autowired
  private ProjetoService service;

  // Salvar projeto
  @PostMapping
  public ResponseEntity<Projeto> cadastrarProjeto(@RequestBody Projeto u) {
    return ResponseEntity.status(HttpStatus.CREATED).body(service.salvarProjeto(u));
  }

  // listar todos os projetos
  @GetMapping
  public ResponseEntity<List<Projeto>> findAllProjetos() {
    return ResponseEntity.ok().body(service.findAllProjetos());
  }

  // buscar Projeto por ID
  @GetMapping(value = "/{id}")
  public ResponseEntity<Optional<Projeto>> buscarProjetoPorId(@PathVariable("id") Long id) {
    Optional<Projeto> ProjetoExiste = service.findProjetoPorId(id);

    if (Objects.isNull(ProjetoExiste)) {
      return ResponseEntity.notFound().build();
    }

    return ResponseEntity.ok().body(service.findProjetoPorId(id));
  }

  // Atualizar projeto
  @PutMapping("/{id}")
  public ResponseEntity<Projeto> atualizarProjeto(@PathVariable Long id, @RequestBody Projeto u) {
    Optional<Projeto> ProjetoExiste = service.findProjetoPorId(id);
    if (Objects.isNull(ProjetoExiste)) {
      return ResponseEntity.notFound().build();
    }
    u.setId(id);
    return ResponseEntity.ok().body(service.salvarProjeto(u));
  }

  // deletar projeto por ID
  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deletarProjetoId(@PathVariable Long id) {
    Optional<Projeto> ProjetoExiste = service.findProjetoPorId(id);
    if (Objects.isNull(ProjetoExiste)) {
      return ResponseEntity.notFound().build();
    }
    service.deletarProjeto(id);
    return ResponseEntity.noContent().build();
  }

}
