package com.empresa.querycep.cep.infra;

import com.empresa.querycep.cep.domain.LocalLogConsultaCep;
import com.empresa.querycep.cep.domain.LogConsultaCep;

public interface CepRepository {
    void save(LocalLogConsultaCep localLogConsultaCep);
}
