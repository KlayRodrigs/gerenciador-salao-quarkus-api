package org.gestao.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, unique = true, nullable = false)
    private long id;

    @NotNull
    @Column(name = "nome_completo")
    private String nome_completo;

    @NotNull
    @Column(name = "celular")
    private String celular;

    @Column(name = "divida")
    private Double divida;

    @CreationTimestamp
    @Column(name = "criado_em")
    private LocalDateTime criado_em;

}
