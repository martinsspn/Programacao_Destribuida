package br.ufrn.chatweb.repository;

import br.ufrn.chatweb.model.Message;
import br.ufrn.chatweb.repository.generic.GenericRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MessageRepository extends GenericRepository<Message> {
    @Query(value = "select * from " +
            "(select m.id, m.usuario_remetente_id, m.usuario_destinatario_id, m.message, m.date " +
            "from message m, usuario u " +
            "where (?1 in (select u.telefone " +
            "              from usuario u, message m " +
            "              where u.id = m.usuario_remetente_id) " +
            "   and ?2 in (select u.telefone " +
            "              from usuario u, message m " +
            "              where u.id = m.usuario_destinatario_id)) " +
            "   or (?2 in (select u.telefone " +
            "              from usuario u, message m " +
            "              where u.id = m.usuario_remetente_id) " +
            "   and ?1 in (select u.telefone " +
            "              from usuario u, message m " +
            "              where u.id = m.usuario_destinatario_id))) as s " +
            "group by s.id, s.usuario_remetente_id, s.usuario_destinatario_id, s.message, s.date " +
            "order by s.date ", nativeQuery = true)
    List<Message> findByTelefones(String remetente, String destinatario);
}

