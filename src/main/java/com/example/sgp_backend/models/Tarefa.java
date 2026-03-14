package com.example.sgp_backend.models;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity(name = "tb_tarefas")
@AllArgsConstructor
@NoArgsConstructor
public class Tarefa {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @Column(nullable = false, length = 25)
  private String titulo;

  @Column(nullable = false, columnDefinition = "TEXT")
  private String descricao;

  @Column(nullable = false)
  private Date dataInicio;

  @Column(nullable = false)
  private Date dataConclusao;

  @ManyToOne
  @JoinColumn(nullable = false)
  private Projeto projeto;

  @ManyToOne
  @JoinColumn(nullable = false)
  private Usuario responsavel;

}
