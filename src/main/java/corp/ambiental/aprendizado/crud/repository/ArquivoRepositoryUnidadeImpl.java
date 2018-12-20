package corp.ambiental.aprendizado.crud.repository;

import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Component
public class ArquivoRepositoryUnidadeImpl extends ArquivoRepositoryImpl{

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    protected EntityManager getEntityManager() {
        return entityManager;
    }
}
