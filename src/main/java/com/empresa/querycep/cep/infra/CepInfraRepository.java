package com.empresa.querycep.cep.infra;

import com.empresa.querycep.cep.domain.LogConsultaCep;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Repository;

@Repository
@Log4j2
@RequiredArgsConstructor
public class CepInfraRepository implements CepRepository {
    private final CepSpringDataJPARepository cepSpringDataJPARepository;

    @Override
    public void save(LogConsultaCep logConsultaCep) {
        log.debug("[start] CepInfraRepository - save");
        cepSpringDataJPARepository.save(logConsultaCep);
        log.debug("[finish] CepInfraRepository - save");
    }

}
