package corp.ambiental.aprendizado.crud.model.mapper;

import corp.ambiental.aprendizado.crud.model.Cliente;
import corp.ambiental.aprendizado.crud.model.dto.ClienteDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ClienteMapper extends EntityMapper<ClienteDTO,Cliente> {

    @Override
    ClienteDTO toDto(Cliente cliente);

    @Override
    Cliente toEntity(ClienteDTO clienteDto);

}
