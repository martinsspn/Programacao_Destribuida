package br.ufrn.chatweb.service;

import br.ufrn.chatweb.model.Message;
import br.ufrn.chatweb.repository.MessageRepository;
import br.ufrn.chatweb.service.generic.AbstractService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageService extends AbstractService<Message, MessageRepository> {
    public MessageService(MessageRepository repository) {
        super(repository);
    }

    public List<Message> findByTelefones(String remetente, String destinatario){
        return repository.findByTelefones(remetente, destinatario);
    }
}
