package com.empresa.querycep.cep.application.dto;

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
    private String ibge;
    private String gia;
    private String ddd;
}
