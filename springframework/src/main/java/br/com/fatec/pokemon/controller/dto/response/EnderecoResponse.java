package br.com.fatec.pokemon.controller.dto.response;

public record EnderecoResponse(
        String cep,
        String logradouro,
        String complemento,
        String bairro,
        String localidade,
        String uf
) {
}
