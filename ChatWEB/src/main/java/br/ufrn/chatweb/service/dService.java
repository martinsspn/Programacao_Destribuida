package br.ufrn.chatweb.service;

import br.ufrn.chatweb.model.d;
import br.ufrn.chatweb.repository.de;
import br.ufrn.chatweb.service.generic.AbstractService;

public class dService extends AbstractService<d, de> {
    public dService(de repository) {
        super(repository);
    }
}
