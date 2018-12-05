package corp.ambiental.aprendizado.crud.repository;

import corp.ambiental.aprendizado.crud.model.OrdemServico;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OrdemServicoRepository extends MongoRepository<OrdemServico,Long> {

}
