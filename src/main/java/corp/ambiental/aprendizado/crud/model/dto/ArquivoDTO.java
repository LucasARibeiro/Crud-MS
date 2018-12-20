package corp.ambiental.aprendizado.crud.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

public class ArquivoDTO {

    @JsonProperty("nome")
    private String nome;
    @JsonProperty("formato")
    private String formato;
    @JsonProperty("nomeBucket")
    private String nomeBucket;
    @JsonProperty("parametros")
    private Map<String, FieldDTO> parametros;
    private String entidade;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getFormato() {
        return formato;
    }

    public void setFormato(String formato) {
        this.formato = formato;
    }

    public String getNomeBucket() {
        return nomeBucket;
    }

    public void setNomeBucket(String nomeBucket) {
        this.nomeBucket = nomeBucket;
    }

    public Map<String, FieldDTO> getParametros() {
        return parametros;
    }

    public void setParametros(Map<String, FieldDTO> parametros) {
        this.parametros = parametros;
    }

    public String getEntidade() {
        return entidade;
    }

    public void setEntidade(String entidade) {
        this.entidade = entidade;
    }
}
