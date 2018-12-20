package corp.ambiental.aprendizado.crud.repository;

import corp.ambiental.aprendizado.crud.model.dto.ArquivoDTO;

import javax.persistence.Tuple;
import java.util.List;

public interface ArquivoRepository {

    List<Tuple> realizaConsulta(ArquivoDTO arquivoDTO);

}
