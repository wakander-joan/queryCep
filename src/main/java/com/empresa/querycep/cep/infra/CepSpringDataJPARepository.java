package com.empresa.querycep.cep.infra;

import com.empresa.querycep.cep.domain.LogConsultaCep;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CepSpringDataJPARepository extends JpaRepository<LogConsultaCep, UUID> {
}
