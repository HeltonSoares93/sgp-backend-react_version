package com.example.sgp_backend.models;

import java.util.Date;

import com.example.sgp_backend.enums.UsuarioStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity(name = "tb_usuarios")
@AllArgsConstructor
@NoArgsConstructor
public class Usuario {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false, length = 65)
  private String nome;

  @Column(nullable = false, length = 11)
  private String cpf;

  @Column(nullable = false, length = 75)
  private String email;

  @Column(nullable = false)
  private Date nascimento;

  @Column(nullable = false)
  private String senha;

  @Enumerated(value = EnumType.STRING)
  private UsuarioStatus status;

}
