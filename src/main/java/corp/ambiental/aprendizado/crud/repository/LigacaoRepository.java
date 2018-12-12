package corp.ambiental.aprendizado.crud.repository;

import corp.ambiental.aprendizado.crud.model.Ligacao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface LigacaoRepository extends  PagingAndSortingRepository<Ligacao,Long> {

    @Query("SELECT LIG FROM LIGACAO LIG WHERE LIG.cliente.id = :idCliente")
    List<Ligacao> findByIdCliente(@Param("idCliente") Long idCliente);

    @Override
    Page<Ligacao> findAll(Pageable pegeable);
}
