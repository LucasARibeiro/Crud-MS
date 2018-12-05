package corp.ambiental.aprendizado.crud.environment;

import corp.ambiental.aprendizado.crud.model.Cliente;

public class ClienteEnvironment {
    public static Cliente criar(){
        Cliente cliente = new Cliente();

        cliente.setId(1L);
        cliente.setNome("Lucas Adriano");

        return cliente;
    }
}
