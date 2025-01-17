package com.empresa.querycep.cep.domain;

import com.empresa.querycep.cep.infra.ConsultaCepResponse;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Entity
@Getter
@ToString
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor(access = AccessLevel.PACKAGE)
public class LocalLogConsultaCep {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(columnDefinition = "uuid", updatable = false, unique = true, nullable = false)
    private UUID id;
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
    @Column(nullable = false)
    private String dataHora;
    @Column(nullable = false)
    private Boolean statusEnvio;

    public LocalLogConsultaCep(ConsultaCepResponse cepResponse) {
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
        this.dataHora = LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        this.statusEnvio = true;
    }
}
