package corp.ambiental.aprendizado.crud.service.converter;

import corp.ambiental.aprendizado.crud.model.dto.LigacaoDTO;
import corp.ambiental.aprendizado.crud.model.Ligacao;
import corp.ambiental.aprendizado.crud.model.mapper.LigacaoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class LigacaoDTOConverter implements Converter<Ligacao,LigacaoDTO> {

    @Autowired
    private LigacaoMapper mapper;

    @Override
    public LigacaoDTO convert(Ligacao source) {
        return mapper.toDto(source);
    }
}
