package com.example.sgp_backend.models;

import java.util.Date;

import com.example.sgp_backend.enums.TarefaStatus;
import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
  @Column(nullable = false, length = 100)
  private String titulo;

  @Column(nullable = false, columnDefinition = "TEXT")
  private String descricao;

  @Column(nullable = false)
  private Date dataInicio;

  @Column(nullable = false)
  private Date dataConclusao;

  @JsonBackReference
  @ManyToOne
  @JoinColumn(nullable = false)
  private Projeto projeto;

  @Enumerated(value = EnumType.STRING)
  @Column(nullable = false)
  private TarefaStatus status;

  @ManyToOne
  @JoinColumn(nullable = false)
  private Usuario responsavel;

}

// "havia um relacionamento bidirecional entre Projeto e Tarefa que causava uma
// recursividade infinita (loop) na serialização do JSON.
// Inicialmente foi utilizado o @JsonIgnore, mas ele impedia a desserialização
// no momento do POST, enviando dados nulos para o banco. A solução encontrada
// foi utilizar @JsonManagedReference no lado 'One' (Projeto) e
// @JsonBackReference no lado 'Many' (Tarefa), garantindo a quebra do loop no
// GET e a integridade dos dados no POST."