package com.empresa.querycep.cep.application.api;

import com.empresa.querycep.cep.application.dto.CepDetalhadoResponse;
import com.empresa.querycep.cep.application.service.CepService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Log4j2
@RequiredArgsConstructor
public class CepController implements CepAPI {
    private final CepService cepService;

    @Override
    public CepDetalhadoResponse buscaCep(String cep) {
        log.debug("[start] CepController - buscaCep");
        CepDetalhadoResponse cepResponse = cepService.buscaCep(cep);
        log.debug("[finish] CepController - buscaCep");
        return cepResponse;
    }
}
