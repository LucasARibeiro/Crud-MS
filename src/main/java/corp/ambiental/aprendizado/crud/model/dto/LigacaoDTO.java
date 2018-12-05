package corp.ambiental.aprendizado.crud.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = false)
public class LigacaoDTO implements Serializable {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("cidade")
    private String cidade;

    @JsonProperty("cliente")
    private Long cliente;

    @JsonProperty("logradouro")
    private String logradouro;

    @JsonProperty("cep")
    private String cep;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public void setCliente(Long cliente) {
        this.cliente = cliente;
    }

    public Long getCliente() {
        return cliente;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }
}
