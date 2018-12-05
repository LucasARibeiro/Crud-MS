package corp.ambiental.aprendizado.crud.model.mapper;

import corp.ambiental.aprendizado.crud.model.dto.OrdemServicoDTO;
import corp.ambiental.aprendizado.crud.model.OrdemServico;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrdemServicoMapper extends  EntityMapper<OrdemServicoDTO, OrdemServico>{

    @Override
    OrdemServicoDTO toDto(OrdemServico ordemServico);

    @Override
    OrdemServico toEntity(OrdemServicoDTO ordemServicoDto);


}
