package com.empresa.querycep.cep.domain;

import com.empresa.querycep.cep.infra.ConsultaCepResponse;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor (access = AccessLevel.PACKAGE)
@NoArgsConstructor (access = AccessLevel.PUBLIC)
public class LogConsultaCep {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(columnDefinition = "uuid", updatable = false, unique = true, nullable = false)
    private UUID id;
    @Column(nullable = false)
    private String cep;
    @Column(nullable = false)
    private String dataHora;
    @Column(nullable = false)
    private Boolean statusEnvio;


    public LogConsultaCep(ConsultaCepResponse cepResponse) {
        this.cep = cepResponse.getCep();
        this.dataHora = String.valueOf(LocalDateTime.now());
        this.statusEnvio = true;
    }
}
