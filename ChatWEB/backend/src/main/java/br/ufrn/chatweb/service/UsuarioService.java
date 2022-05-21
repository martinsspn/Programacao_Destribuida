package br.ufrn.chatweb.service;

import br.ufrn.chatweb.model.Usuario;
import br.ufrn.chatweb.repository.UsuarioRepository;
import br.ufrn.chatweb.service.generic.AbstractService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioService extends AbstractService<Usuario, UsuarioRepository> {
    public UsuarioService(UsuarioRepository repository) {
        super(repository);
    }

    public Optional<Usuario> findByTelefone(String telefone){
        return repository.findByTelefone(telefone);
    }
}
