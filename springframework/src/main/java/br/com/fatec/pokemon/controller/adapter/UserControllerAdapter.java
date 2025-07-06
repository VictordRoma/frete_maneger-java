package br.com.fatec.pokemon.controller.adapter;

import br.com.fatec.pokemon.controller.dto.request.UserRequest;
import br.com.fatec.pokemon.controller.dto.response.EnderecoResponse;
import br.com.fatec.pokemon.controller.dto.response.UserResponse;
import br.com.fatec.pokemon.entity.Endereco;
import br.com.fatec.pokemon.entity.User;
import br.com.fatec.pokemon.entity.StatusEntrega;

import java.util.UUID;

public class UserControllerAdapter {

    private UserControllerAdapter() {
    }

    public static UserResponse cast(User user, double frete) {
        return new UserResponse(
                user.id(),
                user.nome(),
                user.email(),
                new EnderecoResponse(
                        user.endereco().cep(),
                        user.endereco().logradouro(),
                        user.endereco().complemento(),
                        user.endereco().bairro(),
                        user.endereco().localidade(),
                        user.endereco().uf()),
                user.status().name(),
                frete// status convertido para String
        );
    }

    public static User cast(UserRequest request) {
        return new User(
                UUID.randomUUID().toString(),
                request.name(),
                request.email(),
                new Endereco(
                        request.cep(),
                        null,
                        null,
                        null,
                        null,
                        null),
                StatusEntrega.PROCESSANDO // status inicial padrão
        );
    }
}

