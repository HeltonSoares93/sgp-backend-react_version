package com.example.sgp_backend.models;

import java.util.Date;
import java.util.List;

import com.example.sgp_backend.enums.ProjetoStatus;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity(name = "tb_projetos")
@AllArgsConstructor
@NoArgsConstructor
public class Projeto {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false, length = 45)
  private String titulo;

  @Column(nullable = false, columnDefinition = "TEXT")
  private String descricao;

  @Column(nullable = false)
  private Date dataInicio;

  @Column(nullable = false)
  private Date dataConclusao;

  @Column(nullable = false)
  @Enumerated(value = EnumType.STRING)
  private ProjetoStatus status;

  @ManyToOne
  @JoinColumn(nullable = false)
  private Usuario responsavel;

  @JsonManagedReference
  @OneToMany(mappedBy = "projeto", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<Tarefa> tarefas;

}

// "havia um relacionamento bidirecional entre Projeto e Tarefa que causava uma
// recursividade infinita (loop) na serialização do JSON.
// Inicialmente foi utilizado o @JsonIgnore, mas ele impedia a desserialização
// no momento do POST, enviando dados nulos para o banco. A solução encontrada
// foi utilizar @JsonManagedReference no lado 'One' (Projeto) e
// @JsonBackReference no lado 'Many' (Tarefa), garantindo a quebra do loop no
// GET e a integridade dos dados no POST."