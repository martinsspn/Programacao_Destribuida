package br.ufrn.chatweb.service.generic;

import br.ufrn.chatweb.model.generic.AbstractEntity;

import java.util.List;
import java.util.Optional;

public interface IGenericService <E extends AbstractEntity> {
    List<E> findAll();
    E create(E entity);
    Optional<E> findById(Long id);
    Optional<E> update(Long id, E entity);
    Boolean delete(Long id);
}
