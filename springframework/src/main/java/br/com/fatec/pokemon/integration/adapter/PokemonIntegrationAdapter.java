package br.com.fatec.pokemon.integration.adapter;

import br.com.fatec.pokemon.entity.Endereco;
import br.com.fatec.pokemon.integration.dto.ViaCepResponse;

public class PokemonIntegrationAdapter {
    private PokemonIntegrationAdapter() {
    }

    public static Endereco cast(ViaCepResponse response) {
        return new Endereco(
                response.cep(),
                response.logradouro(),
                response.complemento(),
                response.bairro(),
                response.localidade(),
                response.uf());
    }
}
