package com.empresa.querycep.cep.application.service;

import com.empresa.querycep.cep.application.dto.CepDetalhadoResponse;
import com.empresa.querycep.cep.infra.ConsultaCepResponse;
import com.empresa.querycep.cep.infra.WiremockClientFeign;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Service
@Log4j2
@RequiredArgsConstructor
public class CepApplicationService implements CepService {
    private final WiremockClientFeign wiremockClientFeign;

    @Override
    public CepDetalhadoResponse buscaCep(String cep) {
        log.debug("[start] CepApplicationService - buscaCep");
        ConsultaCepResponse cepResponse = wiremockClientFeign.consultaCep(cep);
        log.debug("[finish] CepApplicationService - buscaCep");
        return new CepDetalhadoResponse(cepResponse);
    }
}
