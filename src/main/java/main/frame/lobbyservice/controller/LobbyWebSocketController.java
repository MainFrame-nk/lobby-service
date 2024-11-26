package main.frame.lobbyservice.controller;

import main.frame.lobbyservice.dto.PlayerAction;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RestController;

@RestController
@MessageMapping("/lobby") // Общий префикс для обработки сообщений
public class LobbyWebSocketController {

    private final SimpMessagingTemplate messagingTemplate;

    public LobbyWebSocketController(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    // Обработка действия игрока
    @MessageMapping("/action")
    public void handlePlayerAction(@Payload PlayerAction action, @Header("simpSessionId") String sessionId) {
        // Логика обработки действия игрока
        System.out.println("Player action received: " + action);

        // Оповещение игроков в лобби
        messagingTemplate.convertAndSend("/topic/lobby/" + action.getLobbyId(), action);
    }
}
