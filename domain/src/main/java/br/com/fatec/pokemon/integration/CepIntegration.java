package br.com.fatec.pokemon.integration;

import br.com.fatec.pokemon.entity.Endereco;

public interface CepIntegration {
    Endereco getCep(String cep);
}
