package corp.ambiental.aprendizado.crud.resource;

import corp.ambiental.aprendizado.crud.model.dto.OrdemServicoDTO;
import corp.ambiental.aprendizado.crud.service.OrdemServicoService;
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
@RequestMapping("/api/cadastro/ordemSevico")
public class OrdemServicoResource {

    @Autowired
    private OrdemServicoService ordemServicoService;


    @PostMapping(value = "/novaOrdem")
    private ResponseEntity<OrdemServicoDTO> criar(@RequestBody OrdemServicoDTO ordemServicoDto) {

        OrdemServicoDTO ordemServicoSalva = ordemServicoService.criar(ordemServicoDto);

        URI uriRetorno = ServletUriComponentsBuilder
                .fromCurrentContextPath()
                .path("/api/cadastro/ordemServico/novaOrdem/id/{id}")
                .build(ordemServicoSalva.getId());

        return ResponseEntity
                .created(uriRetorno)
                .body(ordemServicoSalva);
    }

    @GetMapping(value = "/consultar/{idOS}")
    public ResponseEntity<OrdemServicoDTO> consultar(@PathVariable Long idOS) {
        return ResponseEntity.ok().body(ordemServicoService.buscarPorIdOS(idOS));

    }

    @GetMapping(value = "listar")
    public ResponseEntity<List<OrdemServicoDTO>> listarTodos(@RequestParam(value = "size", required = false) Integer size,
                                                             @RequestParam(value = "page", required = false) Integer page) {

        PageRequest pageable = PageRequest.of(
                Objects.isNull(page) ? 0 : page,
                Objects.isNull(size) ? 1 : size);

        return ResponseEntity.ok().body(ordemServicoService.listarTodos(pageable));

    }

    @DeleteMapping(value = "/excluir/{idOS}")
    public ResponseEntity<OrdemServicoDTO> excluir(@PathVariable Long idOS) {
        ordemServicoService.excluir(idOS);

        return ResponseEntity.noContent().build();
    }


    @PutMapping(value = "/atualizar")
    public ResponseEntity<OrdemServicoDTO> atualizar(@Valid @RequestBody OrdemServicoDTO novaOS) {
        OrdemServicoDTO osAtualizadaDto = ordemServicoService.atualizar(novaOS);

        return ResponseEntity.ok().body(osAtualizadaDto);

    }

}
