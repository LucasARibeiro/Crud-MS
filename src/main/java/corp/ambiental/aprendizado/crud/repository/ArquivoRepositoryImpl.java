package corp.ambiental.aprendizado.crud.repository;

import corp.ambiental.aprendizado.crud.model.dto.ArquivoDTO;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.Tuple;
import java.util.List;

@Component
public abstract class ArquivoRepositoryImpl implements ArquivoRepository {

    protected abstract EntityManager getEntityManager();

    @Override
    public List<Tuple> realizaConsulta(ArquivoDTO arquivoDTO) {

        Query nativeQuery;
        StringBuilder sql = new StringBuilder("SELECT * FROM %s WHERE 1 = 1");
        arquivoDTO.getParametros().forEach((key, value) -> {
            sql.append(" AND ")
                    .append(key)
                    .append(" = ");
            if (value.getTipo().equals("String")) {
                sql.append("'").append(value.getValue()).append("'");
            } else {
                sql.append(value.getValue());
            }
        });
        String sqlCompleto = String.format(sql.toString(), arquivoDTO.getEntidade());
        nativeQuery = getEntityManager().createNativeQuery(sqlCompleto, Tuple.class);
        return nativeQuery.getResultList();
    }

}
