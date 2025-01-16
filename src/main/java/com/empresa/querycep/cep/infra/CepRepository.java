package com.empresa.querycep.cep.infra;

import com.empresa.querycep.cep.domain.LogConsultaCep;

public interface CepRepository {
    void save(LogConsultaCep logConsultaCep);
}
