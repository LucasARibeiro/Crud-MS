package corp.ambiental.aprendizado.crud.model;


import corp.ambiental.aprendizado.crud.model.dto.FieldDTO;

import java.util.Map;

public class Arquivo {

    private Long id;
    private String nome;
    private String formato;
    private String nomeBucket;
    private Map<String, FieldDTO> parametros;
    private String entidade;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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
