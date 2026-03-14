package com.example.sgp_backend.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.sgp_backend.models.Usuario;
import com.example.sgp_backend.repositories.UsuarioRepository;

@Service
public class UsuarioService {

  @Autowired
  private UsuarioRepository repository;

  public List<Usuario> findAllUsuarios() {
    return repository.findAll();
  }

  public Optional<Usuario> findUsuarioPorId(Long id) {
    return repository.findById(id);
  }

  public Usuario salvarUsuario(Usuario u) {
    return repository.save(u);
  }


  public void deletarUsuario(Long id) {
    repository.deleteById(id);
  }

}
