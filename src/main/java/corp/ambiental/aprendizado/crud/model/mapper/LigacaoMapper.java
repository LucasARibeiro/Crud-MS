package corp.ambiental.aprendizado.crud.model.mapper;

import corp.ambiental.aprendizado.crud.model.dto.LigacaoDTO;
import corp.ambiental.aprendizado.crud.model.Ligacao;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;


@Mapper(componentModel = "spring")
public interface LigacaoMapper extends EntityMapper<LigacaoDTO, Ligacao> {

    @Override
    @Mappings({
            @Mapping(source = "cliente.id", target = "cliente")
    })
    LigacaoDTO toDto(Ligacao ligacao);

    @Override
    @Mappings({
            @Mapping(source = "cliente", target = "cliente.id")
    })

    Ligacao toEntity(LigacaoDTO ligacaoDTO);
}

