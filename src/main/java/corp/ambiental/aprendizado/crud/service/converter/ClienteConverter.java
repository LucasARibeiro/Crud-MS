package corp.ambiental.aprendizado.crud.service.converter;

import corp.ambiental.aprendizado.crud.model.Cliente;
import corp.ambiental.aprendizado.crud.model.dto.ClienteDTO;
import corp.ambiental.aprendizado.crud.model.mapper.ClienteMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ClienteConverter implements Converter<ClienteDTO,Cliente> {

    @Autowired
    private ClienteMapper mapper;

    @Override
    public Cliente convert(ClienteDTO source) {
        return mapper.toEntity(source);
    }
}
