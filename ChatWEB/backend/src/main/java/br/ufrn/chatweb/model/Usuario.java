package br.ufrn.chatweb.model;

import br.ufrn.chatweb.model.generic.AbstractEntity;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Usuario extends AbstractEntity {
    private String nome;

    @Column(nullable = false, unique = true)
    private String telefone;
}
