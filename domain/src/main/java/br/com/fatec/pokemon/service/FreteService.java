package br.com.fatec.pokemon.service;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class FreteService {

    @Cacheable(value = "frete-cache", key = "#root.args[0]") // ← CORRIGIDO AQUI
    public double calcularFrete(String uf) {
        return switch (uf.toUpperCase()) {
            case "SP", "PR" -> 0.0;
            case "RJ", "SC", "RS" -> 20.0;
            case "MG", "MT", "MS", "ES" -> 50.0;
            default -> throw new IllegalArgumentException("Estado não atendido para frete: " + uf);
        };
    }
}
