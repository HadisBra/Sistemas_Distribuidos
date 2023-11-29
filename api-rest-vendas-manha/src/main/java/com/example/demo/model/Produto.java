package com.example.demo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Manoel Campos
 */
@Getter @Setter @Entity
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (nullable = false)
    private Long id;

    @NotNull @NotBlank
    private String titulo;

    /**
     * Código de barras.
     */
    @NotNull
    private  String ean;

    @NotNull
    private  String descricao;

    @NotNull @DecimalMin("0.05")
    private  double preco;

    @NotNull
    private int estoque;
}
