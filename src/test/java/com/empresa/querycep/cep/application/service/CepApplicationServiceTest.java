package com.empresa.querycep.cep.application.service;

import com.empresa.querycep.cep.application.dto.CepDetalhadoResponse;
import com.empresa.querycep.cep.domain.LocalLogConsultaCep;
import com.empresa.querycep.cep.infra.CepRepository;
import com.empresa.querycep.cep.infra.ConsultaCepResponse;
import com.empresa.querycep.cep.infra.WiremockClientFeign;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CepApplicationServiceTest {

    @InjectMocks
    private CepApplicationService cepApplicationService;

    @Mock
    private SqsProducerService sqsProducerService;

    @Mock
    private WiremockClientFeign wiremockClientFeign;

    @Mock
    private CepRepository cepRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void buscaCep_CenarioPositivo() {
        // Dado que
        String cep = "12345-678";
        ConsultaCepResponse mockResponse = new ConsultaCepResponse("Cep Exemplo", "Logradouro Exemplo", "Complemento Exemplo", "Unidade Exemplo");
        when(wiremockClientFeign.consultaCep(cep)).thenReturn(mockResponse);

        // Quando
        CepDetalhadoResponse result = cepApplicationService.buscaCep(cep);

        // Verifique
        assertNotNull(result);
        assertEquals(mockResponse.getCep(), result.getCep());
        assertEquals(mockResponse.getLogradouro(), result.getLogradouro());
        assertEquals(mockResponse.getComplemento(), result.getComplemento());
        assertEquals(mockResponse.getUnidade(), result.getUnidade());

        verify(wiremockClientFeign, times(1)).consultaCep(cep);
        verify(cepRepository, times(1)).save(any(LocalLogConsultaCep.class));
        // A validação do envio para o SQS de referência
        // verify(sqsProducerService, times(1)).sendLogToSqs(anyString());
    }

    @Test
    void buscaCep_CenarioNegativo_CepNaoEncontrado() {
        // Dado que
        String cep = "99999-999";
        when(wiremockClientFeign.consultaCep(cep)).thenThrow(new RuntimeException("CEP não encontrado"));

        // Se
        Exception exception = assertThrows(RuntimeException.class, () -> cepApplicationService.buscaCep(cep));
        assertEquals("CEP não encontrado", exception.getMessage());

        // Verifique
        verify(wiremockClientFeign, times(1)).consultaCep(cep);
        verifyNoInteractions(cepRepository);
        verifyNoInteractions(sqsProducerService);
    }
}