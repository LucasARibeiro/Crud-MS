package corp.ambiental.aprendizado.crud.environment;

import corp.ambiental.aprendizado.crud.model.dto.LigacaoDTO;

public class LigacaoDTOEnvironment {
    public static LigacaoDTO criarDTO(Long idLigacao){
        LigacaoDTO ligacaoDTO = new LigacaoDTO();
        ligacaoDTO.setId(idLigacao);
        ligacaoDTO.setCep("87023030");
        ligacaoDTO.setCidade("Maringa");
        ligacaoDTO.setLogradouro("teste rua");
        ligacaoDTO.setCliente(1L);
        return ligacaoDTO;
    }
}
