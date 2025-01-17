package com.gasparbarancelli;

import com.fasterxml.jackson.annotation.JsonClassDescription;
import org.springframework.context.annotation.Description;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Function;

@Service
@Description("Busca posts relacionados a um tópico no blog gasparbarancelli.com")
public class FunctionSearchBlogPosts implements Function<FunctionSearchBlogPosts.Request, FunctionSearchBlogPosts.Response> {

    @JsonClassDescription("Obtem os posts do blog relacionados a um determinado assunto")
    public record Request(String topic) {}
    public record BlogPost(String title, String url) {}
    public record Response(List<BlogPost> posts) {}

    @Override
    public Response apply(Request request) {
        List<BlogPost> allPosts = List.of(
            new BlogPost("Aprendendo Spring Boot", "https://gasparbarancelli.com/posts/spring-boot"),
            new BlogPost("Dicas de Java 17", "https://gasparbarancelli.com/posts/java-17"),
            new BlogPost("Testes com JUnit", "https://gasparbarancelli.com/posts/junit-tests")
        );

        List<BlogPost> filtered = allPosts.stream()
            .filter(post -> post.title().toLowerCase().contains(request.topic().toLowerCase()))
            .toList();

        return new Response(filtered);
    }

}
