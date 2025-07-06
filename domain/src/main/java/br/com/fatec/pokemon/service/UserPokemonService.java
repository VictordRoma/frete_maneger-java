package br.com.fatec.pokemon.service;

import br.com.fatec.pokemon.entity.Endereco;
import br.com.fatec.pokemon.entity.StatusEntrega;
import br.com.fatec.pokemon.entity.User;
import br.com.fatec.pokemon.exception.InternalServerException;
import br.com.fatec.pokemon.exception.NotFoundException;
import br.com.fatec.pokemon.integration.CepIntegration;
import br.com.fatec.pokemon.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;

public class UserPokemonService {

    private static final Logger LOG = LoggerFactory.getLogger(UserPokemonService.class);

    private final UserRepository repository;
    private final CepIntegration integration;
    private final FreteService freteService;

    public UserPokemonService(
            UserRepository repository,
            CepIntegration integration,
            FreteService freteService) {
        this.repository = repository;
        this.integration = integration;
        this.freteService = freteService;
    }

    public User register(User user) {
        try {
            User updateUser = repository.findByName(user.nome());
            return save(updateUser.id(), user);
        } catch (NotFoundException ex) {
            return save(user);
        }
    }

    private User save(User user) {
        return save(user.id(), user);
    }

    private User save(final String id, User user) {
        try {
            Endereco endereco = integration.getCep(user.endereco().cep());
            return repository.save(new User(
                    id,
                    user.nome(),
                    user.email(),
                    endereco,
                    StatusEntrega.PROCESSANDO));
        } catch (Exception ex) {
            LOG.error("Erro na regra de negocio ao salvar: {} na data/hora: {}",
                    ex.getMessage(), LocalDateTime.now());
            throw new InternalServerException(ex);
        }
    }

    public double calcularFrete(String uf) {
        return freteService.calcularFrete(uf);
    }

}