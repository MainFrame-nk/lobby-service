package main.frame.lobbyservice.client;

import main.frame.shared.dto.PlayerDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class GameServiceClient {

    private final WebClient webClient;

    @Autowired
    public GameServiceClient(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("http://game-service").build();
    }

    public PlayerDTO getPlayerById(Long playerId) {
        return webClient.get()
                .uri("/players/{id}", playerId)
                .retrieve()
                .bodyToMono(PlayerDTO.class)
                .block();
    }
    //    private void createPlayerForGame(Long lobbyId, Long userId) {
//        String gameServiceUrl = "http://GAME-SERVICE/players";
//
//        webClientBuilder.build()
//                .post()
//                .uri(gameServiceUrl)
//                .bodyValue(new CreatePlayerRequest(userId, lobbyId))
//                .retrieve()
//                .bodyToMono(Void.class)
//                .block();
//    }
}
