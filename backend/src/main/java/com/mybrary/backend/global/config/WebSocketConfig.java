package com.mybrary.backend.global.config;

import com.mybrary.backend.global.handler.ChatPreHandler;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketTransportRegistration;
import org.springframework.web.socket.server.standard.ServletServerContainerFactoryBean;

@Log4j2
@Configuration
@RequiredArgsConstructor
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    private final ChatPreHandler chatPreHandler;

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/ws")
                .setAllowedOriginPatterns("*");
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.enableSimpleBroker("/queue", "/topic");
        registry.setApplicationDestinationPrefixes("/app");
    }

    @Override
    public void configureClientInboundChannel(ChannelRegistration registration) {
        registration.interceptors(chatPreHandler);
    }

    @Override
    public void configureWebSocketTransport(WebSocketTransportRegistration registry) {
        final int messageSizeLimit = 2048 * 2048;
        log.info("Configuring WebSocket transport settings with messageSizeLimit: {}, sendBufferSizeLimit: {}, sendTimeLimit: {}",
                 messageSizeLimit, messageSizeLimit, messageSizeLimit);
        registry.setMessageSizeLimit(messageSizeLimit);
        registry.setSendBufferSizeLimit(messageSizeLimit);
        registry.setSendTimeLimit(messageSizeLimit);
    }

    @Bean
    public ServletServerContainerFactoryBean createServletServerContainerFactoryBean() {
        log.info(
            "Creating ServletServerContainerFactoryBean with maxTextMessageBufferSize: {}, maxSessionIdleTimeout: {}, asyncSendTimeout: {}, maxBinaryMessageBufferSize: {}",
            2048 * 2048, 2048L * 2048L, 2048L * 2048L, 2048 * 2048);
        ServletServerContainerFactoryBean container = new ServletServerContainerFactoryBean();
        container.setMaxTextMessageBufferSize(2048 * 2048);
        container.setMaxSessionIdleTimeout(2048L * 2048L);
        container.setAsyncSendTimeout(2048L * 2048L);
        container.setMaxBinaryMessageBufferSize(2048 * 2048);
        return container;
    }
}
