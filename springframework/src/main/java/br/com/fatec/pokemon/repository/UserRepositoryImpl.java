package br.com.fatec.pokemon.repository;

import br.com.fatec.pokemon.entity.User;
import br.com.fatec.pokemon.exception.InternalServerException;
import br.com.fatec.pokemon.exception.NotFoundException;
import br.com.fatec.pokemon.repository.adapter.UserRepositoryAdapter;
import br.com.fatec.pokemon.repository.client.UserRepositoryWithMongo;
import br.com.fatec.pokemon.repository.orm.UserOrm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public class UserRepositoryImpl implements UserRepository {
    private static final Logger LOG = LoggerFactory.getLogger(UserRepositoryImpl.class);

    private final UserRepositoryWithMongo repository;

    public UserRepositoryImpl(UserRepositoryWithMongo repository) {
        this.repository = repository;
    }

    @Override
    public User save(User user) {
        try {
            UserOrm orm = UserRepositoryAdapter.cast(user);
            return UserRepositoryAdapter.cast(repository.save(orm));
        } catch (Exception ex) {
            LOG.error("Erro ao salvar usuario: {} o erro aconteceu na data/hora: {}",
                    ex.getMessage(), LocalDateTime.now());
            throw new InternalServerException(ex);
        }
    }

    @Override
    public User findById(final String id) {
        try {
            Optional<UserOrm> optional = repository.findById(id);
            if (optional.isEmpty()) {
                throw new NotFoundException("Endereco nao existe");
            }
            return UserRepositoryAdapter.cast(
                    repository.save(optional.get()));
        } catch (NotFoundException ex) {
            LOG.info("Usuario nao encontrado");
            throw ex;
        } catch (Exception ex) {
            LOG.error("Erro ao procurar usuario por id: {} o erro aconteceu na data/hora: {}",
                    ex.getMessage(), LocalDateTime.now());
            throw new InternalServerException(ex);
        }
    }

    @Override
    public User findByName(final String name) {
        try {
            Optional<UserOrm> optional = repository.findByNome(name);
            if (optional.isEmpty()) {
                throw new NotFoundException("Endereco nao existe");
            }
            return UserRepositoryAdapter.cast(
                    repository.save(optional.get()));
        } catch (NotFoundException ex) {
            throw ex;
        } catch (Exception ex) {
            LOG.error("Erro ao procurar usuario por nome: {} o erro aconteceu na data/hora: {}",
                    ex.getMessage(), LocalDateTime.now());
            throw new InternalServerException(ex);
        }
    }

    @Override
    public void delete(final String id) {
        try {
            repository.deleteById(id);
        } catch (Exception ex) {
            LOG.error("Erro ao deletar usuario: {} o erro aconteceu na data/hora: {}",
                    ex.getMessage(), LocalDateTime.now());
            throw new InternalServerException(ex);
        }
    }
}
