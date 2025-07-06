package br.com.fatec.pokemon.entity;

public record User(
        String id,
        String nome,
        String email,
        Endereco endereco,
        StatusEntrega status
) {
}