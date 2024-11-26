package main.frame.lobbyservice.listener;

import main.frame.lobbyservice.config.RabbitMQConfig;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class LobbyEventListener {

    @RabbitListener(queues = RabbitMQConfig.LOBBY_QUEUE)
    public void handleLobbyEvent(String message) {
        System.out.println("Received RabbitMQ message: " + message);

        // Здесь можно добавить логику обработки (например, уведомить WebSocket)
    }
}
