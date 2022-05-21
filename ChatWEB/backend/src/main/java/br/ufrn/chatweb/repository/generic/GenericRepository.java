package br.ufrn.chatweb.repository.generic;

import br.ufrn.chatweb.model.generic.AbstractEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenericRepository<E extends AbstractEntity> extends JpaRepository<E, Long> {
}