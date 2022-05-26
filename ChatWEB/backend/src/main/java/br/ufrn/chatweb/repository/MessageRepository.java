package br.ufrn.chatweb.repository;

import br.ufrn.chatweb.model.Message;
import br.ufrn.chatweb.repository.generic.GenericRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MessageRepository extends GenericRepository<Message> {
    @Query(value = "select * from message m " +
            "where m.id in (select m.id " +
            "from message m, usuario u, (select m.usuario_remetente_id, m.usuario_destinatario_id " +
            "from message m, usuario u " +
            "where m.usuario_remetente_id = u.id " +
            "and u.telefone like ?1) s " +
            "where m.usuario_destinatario_id = s.usuario_destinatario_id " +
            "and u.id = s.usuario_destinatario_id " +
            "and u.telefone like ?2 " +
            "group by m.id, u.id, s.usuario_remetente_id, s.usuario_destinatario_id) " +
            "or m.id in (select m.id " +
            "from message m, usuario u, (select m.usuario_remetente_id, m.usuario_destinatario_id " +
            "from message m, usuario u " +
            "where m.usuario_remetente_id = u.id " +
            "and u.telefone like ?2) s " +
            "where m.usuario_destinatario_id = s.usuario_destinatario_id " +
            "and u.id = s.usuario_destinatario_id " +
            "and u.telefone like ?1 " +
            "group by m.id, u.id, s.usuario_remetente_id, s.usuario_destinatario_id) ", nativeQuery = true)
    List<Message> findByTelefones(String remetente, String destinatario);
}

