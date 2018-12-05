package corp.ambiental.aprendizado.crud.service;

import corp.ambiental.aprendizado.crud.model.dto.OrdemServicoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
public class ProcessadorArquivosService {

    @Autowired
    private MinioService minioService;


    public File geraArquivo(OrdemServicoDTO ordemServicoDTO){

        return iniciarGeracaoArquivo(ordemServicoDTO);

    }

    private File iniciarGeracaoArquivo(OrdemServicoDTO ordemServicoDTO) {
        String nomeArquivo = ordemServicoDTO.getId() +"-"+ ordemServicoDTO.getDescricao();


        return null;
    }


}
