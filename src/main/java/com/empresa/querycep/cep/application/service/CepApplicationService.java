package com.empresa.querycep.cep.application.service;

import com.empresa.querycep.cep.application.dto.CepDetalhadoResponse;
import com.empresa.querycep.cep.domain.LogConsultaCep;
import com.empresa.querycep.cep.infra.CepRepository;
import com.empresa.querycep.cep.infra.ConsultaCepResponse;
import com.empresa.querycep.cep.infra.WiremockClientFeign;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
@Log4j2
@RequiredArgsConstructor
public class CepApplicationService implements CepService {
    private final SqsProducerService sqsProducerService;
    private final WiremockClientFeign wiremockClientFeign;
    private final CepRepository cepRepository;

    @Override
    public CepDetalhadoResponse buscaCep(String cep) {
        log.debug("[start] CepApplicationService - buscaCep");
        ConsultaCepResponse cepResponse = wiremockClientFeign.consultaCep(cep);
        String currentDateTime = LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        String logMessage = String.format(
                "{\"cep\":\"%s\", \"dataHora\":\"%s\", \"statusEnvio\":true}", cep, currentDateTime );
        sqsProducerService.sendLogToSqs(logMessage);
        log.debug("[finish] CepApplicationService - buscaCep");
        LogConsultaCep log = new LogConsultaCep(cepResponse);
        cepRepository.save(log);
        return new CepDetalhadoResponse(cepResponse);
    }
}
