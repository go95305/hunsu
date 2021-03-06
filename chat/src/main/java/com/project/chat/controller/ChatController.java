package com.project.chat.controller;

import com.project.chat.dto.ChatMessage;
import com.project.chat.repository.ChatRoomRepository;
import com.project.chat.service.ChatService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;

@Slf4j
@CrossOrigin(origins = {"http://localhost:3000","http://i4c102.p.ssafy.io"})
@RequiredArgsConstructor
@Controller
public class ChatController {

    private final static Logger LOG = LoggerFactory.getLogger(ChatController.class);

    private final ChatRoomRepository chatRoomRepository;
    private final ChatService chatService;

    /**
     * websocket "/pub/chat/message"로 들어오는 메시징을 처리한다.
     */
    @MessageMapping("/chat/message")
    public void message(ChatMessage message, @Header("nickname") String nickname) {
        // 로그인 회원 정보로 대화명 설정
        log.info("여기숑");
        message.setSender(nickname);
        // 채팅방 인원수 세팅
        message.setUserCount(chatRoomRepository.getUserCount(message.getRoomId()));
        message.setLikeCount(chatRoomRepository.getLikeCount(message.getRoomId()));
        // Websocket에 발행된 메시지를 redis로 발행(publish)
        chatService.sendChatMessage(message);
    }
    @MessageMapping("/chat/like")
    public void roomLike(ChatMessage message, @Header("nickname") String nickname) {
        // 로그인 회원 정보로 대화명 설정
        message.setSender(nickname);
        // 채팅방 인원수 세팅
        message.setUserCount(chatRoomRepository.getUserCount(message.getRoomId()));
        message.setLikeCount(chatRoomRepository.plusLikeCount(message.getRoomId()));
        // Websocket에 발행된 메시지를 redis로 발행(publish)
        chatService.sendChatMessage(message);
    }
}