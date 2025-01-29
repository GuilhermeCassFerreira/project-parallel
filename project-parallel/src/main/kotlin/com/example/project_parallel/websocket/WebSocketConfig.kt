package com.example.project_parallel.config

import org.springframework.context.annotation.Configuration
import org.springframework.messaging.simp.config.MessageBrokerRegistry
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker
import org.springframework.web.socket.config.annotation.StompEndpointRegistry
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer

@Configuration //inficador de que é uma classse de configuração do spirng
@EnableWebSocketMessageBroker //habilita o uso de websocket com menssagens broker 
class WebSocketConfig : WebSocketMessageBrokerConfigurer { //define a interface de configuração de websocket
    override fun configureMessageBroker(config: MessageBrokerRegistry) { //sobresescreve o metodo de configuração de broker de menssagens
        config.enableSimpleBroker("/topic") //habilita o uso de broker de menssagens simples
        // ex: usuário se inscreve em um tópico e recebe mensagens de outros usuários
        // ex: /topic/logs
        // usuairo se inscreve em /topic/logs e recebe mensagens de logs
        config.setApplicationDestinationPrefixes("/app") // define o prefixo para menssagens de aplicação
        // ex: /app/process
        // menssagens enviadas para /app/process são tratadas como menssagens de aplicação
        // menssagens enviadas para /topic/logs são tratadas como menssagens de tópico
    }

    override fun registerStompEndpoints(registry: StompEndpointRegistry) { //sobrescreve o metodo de registro de endpoints
        registry.addEndpoint("/ws").withSockJS() //registra um endpoint para websocket
        // ex: /ws
        // permite conexão via sockjs
        // usuário se conecta em /ws e pode enviar e receber mensagens
    }
}