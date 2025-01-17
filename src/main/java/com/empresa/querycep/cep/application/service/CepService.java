package com.empresa.querycep.cep.application.service;

import com.empresa.querycep.cep.application.dto.CepDetalhadoResponse;

public interface CepService {
    CepDetalhadoResponse buscaCep(String cep);
}
