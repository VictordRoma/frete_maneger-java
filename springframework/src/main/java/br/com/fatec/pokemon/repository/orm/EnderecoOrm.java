package br.com.fatec.pokemon.repository.orm;

import org.springframework.data.mongodb.core.index.Indexed;

public record EnderecoOrm(
        @Indexed
        String cep,
        String logradouro,
        String complemento,
        String bairro,
        String localidade,
        String uf
) {
}
