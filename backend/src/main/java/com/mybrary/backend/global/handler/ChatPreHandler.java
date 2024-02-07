package com.mybrary.backend.global.handler;

import com.mybrary.backend.global.exception.jwt.UnauthorizedAccessException;
import com.mybrary.backend.global.format.response.ErrorCode;
import com.mybrary.backend.global.jwt.provider.TokenProvider;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.stereotype.Component;

@Log4j2
@Component
@RequiredArgsConstructor
public class ChatPreHandler implements ChannelInterceptor {

    private final TokenProvider tokenProvider;
    private static final String BEARER_PREFIX = "Bearer ";

    @Override
    public Message<?> preSend(Message<?> message, MessageChannel channel) {
        StompHeaderAccessor accessor = StompHeaderAccessor.wrap(message);
        if (accessor.getCommand() == StompCommand.CONNECT) {
            String authorization = accessor.getFirstNativeHeader("Authorization");
            if (!tokenProvider.validateToken(extractAccessToken(authorization))) {
                throw new UnauthorizedAccessException(ErrorCode.ACCESS_TOKEN_NOT_FOUND);
            }
        }
        return message;
    }

    public String extractAccessToken(String authorization) {
        if (authorization != null && authorization.startsWith(BEARER_PREFIX)) {
            return authorization.substring(7);
        }
        return null;
    }
}
