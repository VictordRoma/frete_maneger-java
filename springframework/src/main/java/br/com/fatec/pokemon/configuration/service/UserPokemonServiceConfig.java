package br.com.fatec.pokemon.configuration.service;

import br.com.fatec.pokemon.integration.CepIntegration;
import br.com.fatec.pokemon.repository.UserRepository;
import br.com.fatec.pokemon.service.FreteService;
import br.com.fatec.pokemon.service.UserPokemonService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserPokemonServiceConfig {

    @Bean
    public UserPokemonService userPokemonService(
            UserRepository repository,
            CepIntegration integration,
            FreteService freteService) {
        return new UserPokemonService(repository, integration, freteService);
    }
}
