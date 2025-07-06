package br.com.fatec.pokemon.repository.adapter;

import br.com.fatec.pokemon.entity.Endereco;
import br.com.fatec.pokemon.entity.User;
import br.com.fatec.pokemon.repository.orm.EnderecoOrm;
import br.com.fatec.pokemon.repository.orm.UserOrm;

public class UserRepositoryAdapter {
    private UserRepositoryAdapter() {
    }

    public static UserOrm cast(User user) {
        return new UserOrm(
                user.id(),
                user.nome(),
                user.email(),
                cast(user.endereco()),
                user.status()
        );
    }

    private static EnderecoOrm cast(Endereco endereco) {
        return new EnderecoOrm(
                endereco.cep(),
                endereco.logradouro(),
                endereco.complemento(),
                endereco.bairro(),
                endereco.localidade(),
                endereco.uf());
    }

    public static User cast(UserOrm orm) {
        return new User(
                orm.id(),
                orm.nome(),
                orm.email(),
                cast(orm.endereco()),
                orm.status());
    }

    private static Endereco cast(EnderecoOrm orm) {
        return new Endereco(
                orm.cep(),
                orm.logradouro(),
                orm.complemento(),
                orm.bairro(),
                orm.localidade(),
                orm.uf());
    }

}
