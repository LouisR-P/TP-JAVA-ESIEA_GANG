package com.esiea.tp4AOnlineGame;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class PlayerControllerTest {

    @Test
    void startNewGame() {
        PlayerController player = new PlayerControllerImpl(null, null);
        Game game = new GameImpl();
        game.addPlayer("Name");
        player = player.startNew(game, "Name");
        // coder la fonction addPlayer puis Assertions
    }

    @Test
    void roverPostionOfThePlayer(){
        PlayerController player = new PlayerControllerImpl(null, null);
        Game game = new GameImpl();
        game.addPlayer("Name");
        player = player.startNew(game,"Name");
        assertEquals(game.roverPosition("Name"), player.roverPosition());
    }

}
