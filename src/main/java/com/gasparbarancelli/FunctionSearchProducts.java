package com.gasparbarancelli;

import com.fasterxml.jackson.annotation.JsonClassDescription;
import org.springframework.context.annotation.Description;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Function;

@Service
@Description("Busca de produtos comercializados")
public class FunctionSearchProducts implements Function<FunctionSearchProducts.Request, FunctionSearchProducts.Response> {

    @JsonClassDescription("Obtem uma lista de produtos comercializados filtrando pelo seu nome ou categoria")
    public record Request(String query) {}
    public record Product(String name, String category, String description) {}
    public record Response(List<Product> products) {}

    @Override
    public Response apply(Request request) {
        List<Product> allProducts = List.of(
            new Product("Notebook Dell", "informática", "Notebook para uso profissional"),
            new Product("Cadeira Gamer", "escritório", "Cadeira ergonômica para escritório"),
            new Product("Mouse Sem Fio", "informática", "Mouse sem fio com alta precisão")
        );

        List<Product> filtered = allProducts.stream()
            .filter(p -> p.name().toLowerCase().contains(request.query().toLowerCase()) ||
                         p.category().toLowerCase().contains(request.query().toLowerCase()))
            .toList();

        return new Response(filtered);
    }

}
