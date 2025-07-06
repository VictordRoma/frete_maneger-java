package br.com.fatec.pokemon.repository.client;

import br.com.fatec.pokemon.repository.orm.UserOrm;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepositoryWithMongo extends MongoRepository<UserOrm, String> {
    Optional<UserOrm> findByNome(String name);
}
