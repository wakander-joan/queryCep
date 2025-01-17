package com.empresa.querycep.cep.application.dto;

import com.empresa.querycep.cep.infra.ConsultaCepResponse;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class CepDetalhadoResponse {
    private String cep;
    private String logradouro;
    private String complemento;
    private String unidade;
    private String bairro;
    private String localidade;
    private String uf;
    private String estado;
    private String regiao;
    private String ddd;

    public CepDetalhadoResponse(ConsultaCepResponse cepResponse) {
        this.cep = cepResponse.getCep();
        this.logradouro = cepResponse.getLogradouro();
        this.complemento = cepResponse.getComplemento();
        this.unidade = cepResponse.getUnidade();
        this.bairro = cepResponse.getBairro();
        this.localidade = cepResponse.getLocalidade();
        this.uf = cepResponse.getUf();
        this.estado = cepResponse.getEstado();
        this.regiao = cepResponse.getRegiao();
        this.ddd = cepResponse.getDdd();
    }
}
