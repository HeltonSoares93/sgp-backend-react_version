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

import com.example.sgp_backend.models.Usuario;
import com.example.sgp_backend.services.UsuarioService;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

  @Autowired
  private UsuarioService service;

  // Salvar usuário
  @PostMapping
  public ResponseEntity<Usuario> cadastrarUsuario(@RequestBody Usuario u) {
    return ResponseEntity.status(HttpStatus.CREATED).body(service.salvarUsuario(u));
  }

  // listar todos os usuários
  @GetMapping
  public ResponseEntity<List<Usuario>> findAllUsuarios() {
    return ResponseEntity.ok().body(service.findAllUsuarios());
  }

  // buscar usuario por ID
  @GetMapping(value = "/{id}")
  public ResponseEntity<Optional<Usuario>> buscarUsuarioPorId(@PathVariable("id") Long id) {
    Optional<Usuario> usuarioExiste = service.findUsuarioPorId(id);

    if (usuarioExiste.isEmpty()) {
      return ResponseEntity.notFound().build();
    }

    return ResponseEntity.ok().body(service.findUsuarioPorId(id));
  }

  // Atualizar usuário
  @PutMapping("/{id}")
  public ResponseEntity<Usuario> atualizarUsuario(@PathVariable Long id, @RequestBody Usuario u) {
    Optional<Usuario> usuarioExiste = service.findUsuarioPorId(id);
    if (Objects.isNull(usuarioExiste)) {
      return ResponseEntity.notFound().build();
    }
    u.setId(id);
    return ResponseEntity.ok().body(service.salvarUsuario(u));
  }

  // deletar usuário por ID
  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deletarUsuarioId(@PathVariable Long id) {
    Optional<Usuario> usuarioExiste = service.findUsuarioPorId(id);
    if (Objects.isNull(usuarioExiste)) {
      return ResponseEntity.notFound().build();
    }
    service.deletarUsuario(id);
    return ResponseEntity.noContent().build();
  }

}
