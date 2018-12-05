package corp.ambiental.aprendizado.crud.service.converter;

import corp.ambiental.aprendizado.crud.model.dto.OrdemServicoDTO;
import corp.ambiental.aprendizado.crud.model.OrdemServico;
import corp.ambiental.aprendizado.crud.model.mapper.OrdemServicoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class OrdemServicoConverter implements Converter<OrdemServicoDTO, OrdemServico> {

    @Autowired
    private OrdemServicoMapper mapper;

    @Override
    public OrdemServico convert(OrdemServicoDTO source) {
        return mapper.toEntity(source);
    }
}
