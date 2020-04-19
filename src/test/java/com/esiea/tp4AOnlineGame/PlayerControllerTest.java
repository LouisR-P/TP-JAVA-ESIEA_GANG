package com.esiea.tp4AOnlineGame;

import com.esiea.tp4A.domain.Position;
import org.junit.jupiter.api.Test;

import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PlayerControllerTest {

    private Map<String, GameImpl.Player> players;
    private int planetMapSize;
    private Set<Position> planetMapObtaclesAndPlayers;


//    @Test
//    void startNewGame() {
//        PlayerController player = new PlayerControllerImpl(null, null);
//        Game game = new GameImpl(players, planetMapSize, planetMapObtaclesAndPlayers);
//        game.addPlayer("Name");
//        player = player.startNew(game, "Name");
//        // coder la fonction addPlayer puis Assertions
//    }
//
//    @Test
//    void roverPostionOfThePlayer(){
//        PlayerController player = new PlayerControllerImpl(null, null);
//        Game game = new GameImpl(players, planetMapSize, planetMapObtaclesAndPlayers);
//        game.addPlayer("Name");
//        player = player.startNew(game,"Name");
//        assertEquals(game.roverPosition("Name"), player.roverPosition());
//    }

}
