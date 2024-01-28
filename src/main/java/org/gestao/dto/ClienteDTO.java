package org.gestao.dto;

import jakarta.enterprise.context.ApplicationScoped;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApplicationScoped
public class ClienteDTO {
    private String nome_completo;
    private String celular;
    private Double divida;
}
