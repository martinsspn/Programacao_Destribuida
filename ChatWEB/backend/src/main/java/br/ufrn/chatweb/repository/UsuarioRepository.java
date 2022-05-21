package br.ufrn.chatweb.repository;

import br.ufrn.chatweb.model.Usuario;
import br.ufrn.chatweb.repository.generic.GenericRepository;

import java.util.Optional;

public interface UsuarioRepository extends GenericRepository<Usuario> {
    public Optional<Usuario> findByTelefone(String telefone);
}
