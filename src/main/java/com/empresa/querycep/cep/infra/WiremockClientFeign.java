package com.empresa.querycep.cep.infra;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient (name = "wiremockClientFeign", url ="http://localhost:8080/api/test/" )
public interface WiremockClientFeign {
    @GetMapping (value = "{cep}")
    ConsultaCepResponse consultaCep(@PathVariable String cep);
}
