package corp.ambiental.aprendizado.crud.environment;

import corp.ambiental.aprendizado.crud.model.dto.ClienteDTO;

public class ClienteDTOEnvironment {

    public static ClienteDTO criarDTO(Long idCliente){
        ClienteDTO clienteDto = new ClienteDTO();
        clienteDto.setId(idCliente);
        clienteDto.setNome("Teste");
     return clienteDto;
    }


}
