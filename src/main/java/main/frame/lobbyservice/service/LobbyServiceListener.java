package main.frame.lobbyservice.service;

import main.frame.lobbyservice.config.RabbitMQConfig;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
public class LobbyServiceListener {
    @RabbitListener(queues = RabbitMQConfig.LOBBY_QUEUE)
    public void handleMessage(String message) {
        System.out.println("Сообщение получено: " + message);

        // Пример: извлечение данных из сообщения
        if (message.contains("player_ready")) {
            Long playerId = extractPlayerId(message);
            markPlayerAsReady(playerId);
        } else if (message.contains("request_lobby_state")) {
            Long lobbyId = extractLobbyId(message);
            sendLobbyState(lobbyId);
        }
    }

    private Long extractPlayerId(String message) {
        // Извлечение ID игрока из сообщения
        return Long.parseLong(message.split(":")[1]);
    }

    private Long extractLobbyId(String message) {
        // Извлечение ID лобби из сообщения
        return Long.parseLong(message.split(":")[1]);
    }

    private void markPlayerAsReady(Long playerId) {
        System.out.println("Marking player as ready: " + playerId);
        // Логика пометки игрока как готового в базе данных
    }

    private void sendLobbyState(Long lobbyId) {
        System.out.println("Sending lobby state for lobby: " + lobbyId);
        // Логика отправки состояния лобби
    }

//    // Обработка событий лобби
//    @RabbitListener(queues = RabbitMQConfig.LOBBY_EVENTS_QUEUE)
//    public void handleLobbyEvents(String message) {
//        System.out.println("Lobby Event Received: " + message);
//        // Логика обработки событий лобби
//    }
//
//    // Обработка действий игроков
//    @RabbitListener(queues = RabbitMQConfig.PLAYER_ACTIONS_QUEUE)
//    public void handlePlayerActions(String message) {
//        System.out.println("Player Action Received: " + message);
//        // Логика обработки действий игроков
//    }
}
