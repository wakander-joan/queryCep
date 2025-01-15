package com.empresa.querycep.cep.application.api;

import com.empresa.querycep.cep.application.dto.CepDetalhadoResponse;
import jakarta.validation.constraints.Size;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cep")
public interface CepAPI {

    @GetMapping("/buscaCep/{cep}")
    @ResponseStatus(code = HttpStatus.OK)
    CepDetalhadoResponse buscaCep (@PathVariable @Size(min = 8, max = 8, message = "O CEP deve conter exatamente 8 caracteres numerais")
                                       String cep);


}
