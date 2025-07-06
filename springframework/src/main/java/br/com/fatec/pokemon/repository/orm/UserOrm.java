package br.com.fatec.pokemon.repository.orm;

import br.com.fatec.pokemon.entity.StatusEntrega;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(value = "user")
public record UserOrm(
        @Id
        String id,
        @Indexed
        String nome,
        String email,
        EnderecoOrm endereco,
        StatusEntrega status
) {
}
