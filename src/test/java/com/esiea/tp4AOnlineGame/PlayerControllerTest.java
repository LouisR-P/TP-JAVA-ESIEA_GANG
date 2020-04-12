package com.esiea.tp4AOnlineGame;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
