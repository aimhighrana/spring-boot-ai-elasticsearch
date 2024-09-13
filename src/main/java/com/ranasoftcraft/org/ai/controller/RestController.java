package com.ranasoftcraft.org.ai.controller;

import com.ranasoftcraft.org.ai.entity.AiRequest;
import com.ranasoftcraft.org.ai.entity.AiResponse;
import com.ranasoftcraft.org.ai.play.ChatPlay;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
@org.springframework.web.bind.annotation.RestController
@RequiredArgsConstructor
public class RestController {

    private final ChatPlay chatPlay;

    @GetMapping("/chat")
    public ResponseEntity<AiResponse> chat(@RequestParam String message) throws IOException {
        return ResponseEntity.status(HttpStatus.OK).body(chatPlay.doChat(new AiRequest(message)));
    }
}
