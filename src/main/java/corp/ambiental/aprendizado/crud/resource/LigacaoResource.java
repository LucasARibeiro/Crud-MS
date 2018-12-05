package corp.ambiental.aprendizado.crud.resource;

import corp.ambiental.aprendizado.crud.model.dto.LigacaoDTO;
import corp.ambiental.aprendizado.crud.service.LigacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/cadastro/ligacao")
public class LigacaoResource {

    @Autowired
    private LigacaoService ligacaoService;

    @GetMapping(value = "/consultarPeloIdLigacao/{idLigacao}")
    public ResponseEntity<LigacaoDTO> consultar(@PathVariable Long idLigacao) {

        return ResponseEntity.ok().body(ligacaoService.buscarPorIdLigacao(idLigacao));
    }

    @PostMapping(value = "/nova")
    public ResponseEntity<LigacaoDTO> criar(@Valid @RequestBody LigacaoDTO ligacaoDTO) {

        LigacaoDTO ligacaoDtoSaved = ligacaoService.salvar(ligacaoDTO);

        URI uriRetorno = ServletUriComponentsBuilder
                .fromCurrentContextPath()
                .path("/api/cadastro/ligacao/nova/idLigacao/{idLigacao}")
                .build(ligacaoDtoSaved.getId());

        return ResponseEntity
                .created(uriRetorno)
                .body(ligacaoDtoSaved);
    }

    @DeleteMapping(value = "/excluir/{idLigacao}")
    public ResponseEntity<LigacaoDTO> excluir(@PathVariable Long idLigacao) {

        ligacaoService.excluir(idLigacao);

        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/nova")
    public ResponseEntity<LigacaoDTO> atualizar(@Valid @RequestBody LigacaoDTO novaLigacaoDto) {

        LigacaoDTO ligacaoAtualizadaDto = ligacaoService.atualizar(novaLigacaoDto);

        return ResponseEntity.ok().body(ligacaoAtualizadaDto);
    }


    @GetMapping(value = "/listar/cliente/{idCliente}")
    public ResponseEntity<List<LigacaoDTO>> listar(@PathVariable Long idCliente) {

        return ResponseEntity.ok().body(ligacaoService.listarPorIdCliente(idCliente));
    }

    @GetMapping(value = "/listar")
    public ResponseEntity<List<LigacaoDTO>> consultarLista(@RequestParam(value = "size", required = false) Integer size,
                                                           @RequestParam(value = "page", required = false) Integer page) {
        PageRequest pageable = PageRequest.of(
                Objects.isNull(page) ? 0 : page,
                Objects.isNull(size) ? 1 : size
        );

        return ResponseEntity.ok().body(ligacaoService.listarTodos(pageable));
    }

}
