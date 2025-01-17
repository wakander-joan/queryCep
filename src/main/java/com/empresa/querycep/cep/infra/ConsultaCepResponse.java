package com.empresa.querycep.cep.infra;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
public class ConsultaCepResponse {
    @JsonProperty("cep")
    private String cep;
    @JsonProperty("logradouro")
    private String logradouro;
    @JsonProperty("complemento")
    private String complemento;
    @JsonProperty("unidade")
    private String unidade;
    @JsonProperty("bairro")
    private String bairro;
    @JsonProperty("localidade")
    private String localidade;
    @JsonProperty("uf")
    private String uf;
    @JsonProperty("estado")
    private String estado;
    @JsonProperty("regiao")
    private String regiao;
    @JsonProperty("ddd")
    private String ddd;


    public ConsultaCepResponse(String cepExemplo, String logradouroExemplo, String complementoExemplo, String unidadeExemplo) {
        this.cep = cepExemplo;
        this.logradouro = logradouroExemplo;
        this.complemento = complementoExemplo;
        this.unidade = unidadeExemplo;
    }
}
