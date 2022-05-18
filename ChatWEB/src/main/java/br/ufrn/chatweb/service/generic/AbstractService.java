package br.ufrn.chatweb.service.generic;

import br.ufrn.chatweb.model.generic.AbstractEntity;
import br.ufrn.chatweb.repository.generic.GenericRepository;

import java.util.List;
import java.util.Optional;

public abstract class AbstractService<E extends AbstractEntity, R extends GenericRepository<E>> implements IGenericService<E>  {
    protected final R repository;

    public AbstractService(R repository) {
        this.repository = repository;
    }

    @Override
    public List<E> findAll(){
        return repository.findAll();
    }

    @Override
    public E create(E entity) {
        return  repository.save(entity);
    }

    @Override
    public Optional<E> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public Optional<E> update(Long id,E entity) {
        return repository
                .findById(id)
                .map(record -> {
                    repository.saveAndFlush(entity);
                    return record;
                });
    }

    @Override
    public Boolean delete(Long id) {
        return repository.findById(id)
                .map(record -> {
                    repository.deleteById(id);
                    return true;
                }).orElse(false);
    }
}