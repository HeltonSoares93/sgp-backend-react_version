package com.example.sgp_backend.models;

import java.util.Date;
import java.util.List;

import com.example.sgp_backend.enums.ProjetoStatus;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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

  @Column(nullable = false, length = 25)
  private String titulo;

  @Column(nullable = false, columnDefinition = "TEXT")
  private String descricao;

  @Column(nullable = false)
  private Date dataInicio;

  @Column(nullable = false)
  private Date dataConclusao;

  @Column(nullable = false)
  private ProjetoStatus status;

  @ManyToOne
  @JoinColumn(nullable = false)
  private Usuario responsavel;

  
  @OneToMany(mappedBy = "projeto", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<Tarefa> tarefas;

}
