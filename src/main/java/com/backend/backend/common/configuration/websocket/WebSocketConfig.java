package com.backend.backend.common.configuration.websocket;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@RequiredArgsConstructor
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry)
    {
        // 구독한 클라이언트들에게 메시지를 보낸다.
        registry.setApplicationDestinationPrefixes("/pub");
        // 구독
        registry.enableSimpleBroker("/sub");

    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) // Client에서 websocket연결할때 사용할 API 경로를 설정해주는 메소드
    {
        registry
                .addEndpoint("/ws") // Stomp endpoint 설정
                .setAllowedOriginPatterns("*")
                .withSockJS();
    }
}
