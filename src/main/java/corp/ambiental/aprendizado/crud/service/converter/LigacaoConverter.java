package corp.ambiental.aprendizado.crud.service.converter;

import corp.ambiental.aprendizado.crud.model.dto.LigacaoDTO;
import corp.ambiental.aprendizado.crud.model.Ligacao;
import corp.ambiental.aprendizado.crud.model.mapper.LigacaoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;


@Component
public class LigacaoConverter implements Converter<LigacaoDTO, Ligacao>{

    @Autowired
    private LigacaoMapper mapper;


    @Override
    public Ligacao convert(LigacaoDTO source) {
        return mapper.toEntity(source);
    }
}
