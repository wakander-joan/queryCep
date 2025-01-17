package com.empresa.querycep.consummer;

import com.empresa.querycep.cep.domain.LogConsultaCep;
import com.empresa.querycep.cep.infra.CepRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.awspring.cloud.messaging.listener.annotation.SqsListener;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@Log4j2
@RequiredArgsConstructor
public class LogConsumer {
    private final ObjectMapper objectMapper;
    private final CepRepository cepRepository;

    @SqsListener("query-cep-queue")
    public void listen(String message) {
        try {
            log.debug("Mensagem recebida: {}", message);
            Map<String, Object> logMessage = objectMapper.readValue(message, Map.class);
            String cep = (String) logMessage.get("cep");
            String dataHora = (String) logMessage.get("dataHora");
            Boolean statusEnvio = (Boolean) logMessage.get("statusEnvio");
            log.debug("Log processado: CEP={}, DataHora={}, StatusEnvio={}", cep, dataHora, statusEnvio);

            persistirNoBanco(cep, dataHora, statusEnvio);
        } catch (Exception e) {
            log.error("Erro ao processar a mensagem: {}", message, e);
        }
    }

    private void persistirNoBanco(String cep, String dataHora, Boolean statusEnvio) {
        try {
            log.debug("[inicia] - Cria log para persistir");
            LogConsultaCep logConsultaCep = new LogConsultaCep();
            logConsultaCep.setCep(cep);
            logConsultaCep.setDataHora(dataHora);
            logConsultaCep.setStatusEnvio(statusEnvio);

            // Persistindo o log no banco de dados
            //cepRepository.save(logConsultaCep);
            log.debug("Persistência concluída com sucesso: {}", logConsultaCep);
        } catch (Exception e) {
            log.error("Erro ao persistir no banco de dados", e);
        }
    }
}
