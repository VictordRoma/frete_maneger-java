package br.com.fatec.pokemon.integration.client;

import br.com.fatec.pokemon.exception.BadRequestException;
import br.com.fatec.pokemon.exception.InternalServerException;
import br.com.fatec.pokemon.exception.NotFoundException;
import feign.Response;
import feign.codec.ErrorDecoder;

public class FeignErrorDecoder implements ErrorDecoder {

    @Override
    public Exception decode(String s, Response response) {
        System.out.println("API STATUS RESPONSE: " + response.status());
        switch (response.status()) {
            case 400:
                throw new BadRequestException("Itens enviados ao Pokemons estao com erros");
            case 404:
                throw new NotFoundException("Endereco não existe");
            default:
                throw new InternalServerException("Erro não mapeado");
        }
    }
}
