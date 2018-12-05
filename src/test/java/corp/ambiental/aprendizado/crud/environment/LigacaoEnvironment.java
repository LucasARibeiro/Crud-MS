package corp.ambiental.aprendizado.crud.environment;

import corp.ambiental.aprendizado.crud.model.Ligacao;

public class LigacaoEnvironment {
    public static Ligacao criar(){
        Ligacao ligacao = new Ligacao();
        ligacao.setId(1L);
        ligacao.setCep("87023323");
        ligacao.setCidade("Maringa");
        ligacao.setLogradouro("rua teste");
        ligacao.setCliente(ClienteEnvironment.criar());

        return ligacao;
    }
}
