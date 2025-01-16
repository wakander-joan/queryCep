package com.empresa.querycep.cep.application.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.sqs.SqsAsyncClient;
import software.amazon.awssdk.services.sqs.model.SendMessageRequest;

@Service
@RequiredArgsConstructor
@Log4j2
public class SqsProducerService {

    private final SqsAsyncClient sqsAsyncClient;
    private static final String QUEUE_URL = "http://sqs.us-east-1.localhost.localstack.cloud:4566/000000000000/query-cep-queue";

    public void sendLogToSqs(String logMessage) {
        try {
            log.info("Enviando log para a fila SQS: {}", logMessage);

            SendMessageRequest request = SendMessageRequest.builder()
                    .queueUrl(QUEUE_URL)
                    .messageBody(logMessage)
                    .build();

            sqsAsyncClient.sendMessage(request).join();

            log.info("Log enviado com sucesso para a fila SQS.");
        } catch (Exception e) {
            log.error("Erro ao enviar log para a fila SQS", e);
        }
    }
}
