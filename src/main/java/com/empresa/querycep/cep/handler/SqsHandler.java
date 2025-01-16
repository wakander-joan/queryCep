package com.empresa.querycep.cep.handler;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.SQSEvent;
import com.empresa.querycep.cep.domain.LogConsultaCep;
import com.empresa.querycep.cep.infra.CepRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Map;

@Log4j2
@Component
@RequiredArgsConstructor
public class SqsHandler implements RequestHandler<SQSEvent, String> {

    private final ObjectMapper objectMapper = new ObjectMapper();
    private final CepRepository cepRepository;

    @Override
    public String handleRequest(SQSEvent event, Context context) {
        log.debug("[start] Processamento das mensagens da fila SQS");

        for (SQSEvent.SQSMessage message : event.getRecords()) {
            try {
                log.debug("Mensagem recebida: {}", message.getBody());

                // Desserializar a mensagem JSON
                Map<String, Object> logMessage = objectMapper.readValue(message.getBody(), Map.class);

                // Extraindo dados
                String cep = (String) logMessage.get("cep");
                String dataHora = (String) logMessage.get("dataHora");
                Boolean statusEnvio = (Boolean) logMessage.get("statusEnvio");

                log.debug("Log processado: CEP={}, DataHora={}, StatusEnvio={}", cep, dataHora, statusEnvio);

                // Persistir no banco
                persistirNoBanco(cep, dataHora, statusEnvio);

            } catch (Exception e) {
                log.error("Erro ao processar a mensagem: {}", message.getBody(), e);
            }
        }

        log.debug("[finish] Processamento das mensagens da fila SQS");
        return "Mensagens processadas com sucesso!";
    }

    private void persistirNoBanco(String cep, String dataHora, Boolean statusEnvio) {
        try {
            log.debug("[inicia] - Cria log para percistir");
            LogConsultaCep logConsultaCep = new LogConsultaCep();
            logConsultaCep.setCep(cep);
            logConsultaCep.setDataHora(dataHora);
            logConsultaCep.setStatusEnvio(statusEnvio);
            log.debug("[finaliza] - Cria log para percistir");

            cepRepository.save(logConsultaCep);
            log.debug("Persistência concluída com sucesso: {}", logConsultaCep);
        } catch (Exception e) {
            log.debug("Erro ao persistir no banco de dados", e);
        }
    }
}
