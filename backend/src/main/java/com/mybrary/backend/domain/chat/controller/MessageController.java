package com.mybrary.backend.domain.chat.controller;


import com.mybrary.backend.domain.chat.service.ChatService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RestController;

@Log4j2
@RestController
@RequiredArgsConstructor
public class MessageController {

    private final ChatService chatService;
    private final SimpMessagingTemplate template;



}