package br.com.fatec.pokemon.controller.dto.request;

import jakarta.validation.constraints.NotBlank;
import org.springframework.validation.annotation.Validated;

@Validated
public record UserRequest(
        @NotBlank(message = "O nome está nulo")
        String name,
        @NotBlank(message = "O email não pode ser vazio")
        String email,
        @NotBlank(message = "O cep não pode ser vazio")
        String cep
) {
}