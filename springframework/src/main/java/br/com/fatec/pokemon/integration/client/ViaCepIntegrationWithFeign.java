package br.com.fatec.pokemon.integration.client;

import br.com.fatec.pokemon.integration.dto.ViaCepResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(
        name = "ViaCepIntegration",
        url = "${viacep.url}")
public interface ViaCepIntegrationWithFeign {
    @GetMapping("/ws/{cep}/json")
    ViaCepResponse getCep(@PathVariable(name = "cep") String cep);
}
