package com.gasparbarancelli;

import com.fasterxml.jackson.annotation.JsonClassDescription;
import org.springframework.context.annotation.Description;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.function.Function;

@Service
@Description("Obtem o preço/valor do produto")
public class FunctionProductPricing implements Function<FunctionProductPricing.Request, FunctionProductPricing.Response> {

    @JsonClassDescription("Encontra o produto por seu nome e retorna o preço/valor atualizado")
    public record Request(String productName) {}
    public record Response(String productName, BigDecimal price, String currency) {}

    @Override
    public Response apply(Request request) {
        BigDecimal price;
        if (request.productName().toLowerCase().contains("notebook")) {
            price = BigDecimal.valueOf(3_500.00);
        } else if (request.productName().toLowerCase().contains("cadeira")) {
            price = BigDecimal.valueOf(999.99);
        } else {
            price = BigDecimal.valueOf(150.99);
        }
        return new Response(request.productName(), price, "BRL");
    }

}