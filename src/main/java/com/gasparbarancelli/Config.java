package com.gasparbarancelli;

import org.springframework.context.annotation.Configuration;

import java.util.function.Function;

@Configuration
public class Config {


    public Function<FunctionSearchBlogPosts.Request, FunctionSearchBlogPosts.Response> blogPostSearch() {
        return new FunctionSearchBlogPosts();
    }

}
