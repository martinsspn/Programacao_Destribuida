package br.ufrn.chatweb.model;

import br.ufrn.chatweb.model.generic.AbstractEntity;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Message extends AbstractEntity {

    @ManyToOne
    @JoinColumn(name = "usuario_remetente_id")
    private Usuario telefoneRemetente;

    @ManyToOne
    @JoinColumn(name = "usuario_destinatario_id")
    private Usuario telefoneDestinatario;

    private String message;

    private Date date;

}
