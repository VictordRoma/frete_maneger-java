package br.com.fatec.pokemon.controller;

import br.com.fatec.pokemon.controller.adapter.UserControllerAdapter;
import br.com.fatec.pokemon.controller.dto.request.UserRequest;
import br.com.fatec.pokemon.controller.dto.response.UserResponse;
import br.com.fatec.pokemon.entity.User;
import br.com.fatec.pokemon.exception.BadRequestException;
import br.com.fatec.pokemon.repository.UserRepository;
import br.com.fatec.pokemon.service.UserPokemonService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pokemon/v1")
public class UserController {
    private final UserRepository repository;
    private final UserPokemonService service;

    public UserController(UserRepository repository, UserPokemonService service) {
        this.repository = repository;
        this.service = service;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/user")
    public UserResponse save(@RequestBody UserRequest request) {
        User user = UserControllerAdapter.cast(request);
        User savedUser = service.register(user);

        double frete = service.calcularFrete(savedUser.endereco().uf());
        return UserControllerAdapter.cast(savedUser, frete);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/user/find-name/{name}")
    public UserResponse getByName(@PathVariable("name") String name) {
        User user = repository.findByName(name);
        double frete = service.calcularFrete(user.endereco().uf());
        return UserControllerAdapter.cast(user, frete);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/user/find-id/{id}")
    public UserResponse getById(@PathVariable("id") String id) {
        User user = repository.findById(id);
        double frete = service.calcularFrete(user.endereco().uf());
        return UserControllerAdapter.cast(user, frete);
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/user/{id}")
    public void delete(@PathVariable("id") String id) {
        repository.delete(id);
    }

}
