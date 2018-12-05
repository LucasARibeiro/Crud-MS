package corp.ambiental.aprendizado.crud.config;

import corp.ambiental.aprendizado.crud.service.converter.ClienteConverter;
import corp.ambiental.aprendizado.crud.service.converter.ClienteDTOConverter;
import corp.ambiental.aprendizado.crud.service.converter.LigacaoConverter;
import corp.ambiental.aprendizado.crud.service.converter.LigacaoDTOConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private LigacaoConverter ligacaoConverter;

    @Autowired
    private LigacaoDTOConverter ligacaoDTOConverter;

    @Autowired
    private ClienteConverter clienteConverter;

    @Autowired
    private ClienteDTOConverter clienteDTOConverter;

    @Override
    public void addFormatters(FormatterRegistry registry){
        registry.addConverter(ligacaoConverter);
        registry.addConverter(clienteConverter);
        registry.addConverter(ligacaoDTOConverter);
        registry.addConverter(clienteDTOConverter);
    }

}

