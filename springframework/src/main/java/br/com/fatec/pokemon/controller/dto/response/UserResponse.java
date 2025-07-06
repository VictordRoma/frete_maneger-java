package br.com.fatec.pokemon.controller.dto.response;

public record UserResponse(
        String id,
        String nome,
        String email,
        EnderecoResponse pokemon,
        String status,
        double frete
) {
}
