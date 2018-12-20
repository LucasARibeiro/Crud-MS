package corp.ambiental.aprendizado.crud.resource;

import corp.ambiental.aprendizado.crud.model.dto.ArquivoDTO;
import corp.ambiental.aprendizado.crud.service.ArquivoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/api/cadastro/arquivo")
public class ArquivoResource {

    @Autowired
    private ArquivoService arquivoService;


    @PostMapping("/novoArquivo")
    public ResponseEntity<ArquivoDTO> criarArquivoEEnviarAoMinio(@RequestBody ArquivoDTO arquivoDTO) throws IOException {
        return ResponseEntity.ok().body(arquivoService.salvarArquivoMinio(arquivoDTO));
    }



}

