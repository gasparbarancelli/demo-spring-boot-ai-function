package com.gasparbarancelli;

import com.fasterxml.jackson.annotation.JsonClassDescription;
import org.springframework.context.annotation.Description;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Function;

@Service
@Description("Obtem as avaliações de determinado produto")
public  class FunctionProductReviews implements Function<FunctionProductReviews.Request, FunctionProductReviews.Response> {

    @JsonClassDescription("Recupera o produto por seu nome e retorna as avaliações feitas por outros compradores")
    public record Request(String productName) {}
    public record Review(String user, String comment, int rating) {}
    public record Response(String productName, List<Review> reviews) {}

    @Override
    public Response apply(Request request) {
        List<Review> reviews;
        if (request.productName().toLowerCase().contains("notebook")) {
            reviews = List.of(
                new Review("Gaspar", "Ótimo produto!", 5),
                new Review("Laura", "Atende bem, mas poderia ter mais memória", 4)
            );
        } else {
            reviews = List.of(
                new Review("Cecília", "Bom custo-benefício.", 4)
            );
        }
        return new Response(request.productName(), reviews);
    }

}