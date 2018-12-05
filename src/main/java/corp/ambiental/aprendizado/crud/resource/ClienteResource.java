package corp.ambiental.aprendizado.crud.resource;

import corp.ambiental.aprendizado.crud.model.dto.ClienteDTO;
import corp.ambiental.aprendizado.crud.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/api/cadastro/cliente")
public class ClienteResource {

    @Autowired
    private ClienteService clienteService;


    @GetMapping(value = "/listar-por-id/{idCliente}")
    public ResponseEntity<ClienteDTO> consultar(@PathVariable Long idCliente) {

        return ResponseEntity.ok().body(clienteService.buscarPorIdCliente(idCliente));
    }

    @PostMapping(value = "/novo")
    public ResponseEntity<ClienteDTO> criar(@Valid @RequestBody ClienteDTO clienteDto) {

        ClienteDTO clienteDtoSalvo = clienteService.salvar(clienteDto);

        URI uriRetorno = ServletUriComponentsBuilder
                .fromCurrentContextPath()
                .path("/api/cadastro/cliente/novo/idCliente/{idCliente}")
                .build(clienteDtoSalvo.getId());

        return ResponseEntity
                .created(uriRetorno)
                .body(clienteDtoSalvo);
    }

    @PutMapping(value = "/novo")
    public ResponseEntity<ClienteDTO> atualizar(@Valid @RequestBody ClienteDTO clienteDTO){

        ClienteDTO clienteDtoAtualizado = clienteService.atualizar(clienteDTO);

        return ResponseEntity
                .ok()
                .body(clienteDtoAtualizado);
    }

    @DeleteMapping(value = "/delete/{idCliente}")
    public ResponseEntity<ClienteDTO> excluir(@PathVariable Long idCliente){

        clienteService.excluir(idCliente);

        return ResponseEntity
                .noContent()
                .build();
    }

}
