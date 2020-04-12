package com.esiea.tp4AOnlineGame;

import com.esiea.tp4A.domain.Position;

import java.util.Set;

public class PlayerControllerImpl implements PlayerController {
    private final Game game;
    private final String playerName;

    public PlayerControllerImpl(Game game, String playerName) {
        this.game = game;
        this.playerName = playerName;
    }

    @Override
    public PlayerController startNew(Game game, String playerName) {
        return new PlayerControllerImpl(game, playerName);
    }

    @Override
    public Position roverPosition() {
        if (this.game == null){
            return null;
        }
        return this.game.roverPosition(this.playerName);
    }

    @Override
    public Set<Position> radar(String playerName) {
        return this.game.radar(this.playerName);
    }

    @Override
    public int laserRange(String playerName) {
        return this.game.laserRange(this.playerName);
    }

    @Override
    public Position move(String playerName, String command) {
        return this.game.move(this.playerName, command);

    }

    @Override
    public Boolean alive(String playerName) {
        return this.game.alive(this.playerName);
    }
}
