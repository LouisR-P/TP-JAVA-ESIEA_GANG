package java.tp4AOlineGame;

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
    public Position roverPosition(String playerName) {
        return game.roverPosition(this.playerName);

    }

    @Override
    public Set<Position> radar(String playerName) {
        return game.radar(this.playerName);
    }

    @Override
    public int laserRange(String playerName) {
        return game.laserRange(this.playerName);
    }

    @Override
    public Position move(String playerName, String command) {
        return game.move(this.playerName, command);

    }

    @Override
    public Boolean alive(String playerName) {
        return game.alive(this.playerName);
    }
}
