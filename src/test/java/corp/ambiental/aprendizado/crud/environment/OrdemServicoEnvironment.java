package corp.ambiental.aprendizado.crud.environment;

import corp.ambiental.aprendizado.crud.model.OrdemServico;

public class OrdemServicoEnvironment {
    public static OrdemServico criar(){
        OrdemServico ordemServico = new OrdemServico();
        ordemServico.setId(1L);
        ordemServico.setDescricao("Descricao OS");
        return ordemServico;
    }
}
