package com.gasparbarancelli;

import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping( "/")
public class ChatbotService {

    private final OpenAiChatModel openAiChatClient;

    public ChatbotService(OpenAiChatModel openAiChatClient) {
        this.openAiChatClient = openAiChatClient;
    }

    @GetMapping
    public String hello(@RequestParam("message") String message) {
        UserMessage userMessage = new UserMessage(message);
        ChatResponse response = openAiChatClient.call(new Prompt(userMessage));
        return response.getResult().getOutput().getContent();
    }

}
