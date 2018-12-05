package corp.ambiental.aprendizado.crud.environment;

import corp.ambiental.aprendizado.crud.model.dto.OrdemServicoDTO;

public class OrdemServicoDTOEnvironment {
    public static OrdemServicoDTO criar(Long id){
        OrdemServicoDTO ordemServicoDto = new OrdemServicoDTO();
        ordemServicoDto.setId(id);
        ordemServicoDto.setDescricao("Descricao OS");
        return ordemServicoDto;
    }
}
