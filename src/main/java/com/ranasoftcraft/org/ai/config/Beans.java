package com.ranasoftcraft.org.ai.config;

import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration @RequiredArgsConstructor
public class Beans {


    @Bean
    public ChatClient chatClient(ChatClient.Builder builder) {
        return builder
                .build();
    }

}
