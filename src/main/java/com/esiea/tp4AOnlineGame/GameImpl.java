package com.esiea.tp4AOnlineGame;

import com.esiea.tp4A.domain.*;

import java.util.Map;
import java.util.Set;

public class GameImpl implements Game {
    private final Map<String, Player> players;
    private final int planetMapSize;
    private final Set<Position> planetMapObstaclesAndPlayers;

    public GameImpl(Map<String, Player> players, int planetMapSize, Set<Position> planetMapObtaclesAndPlayers) {
        this.players = players;
        this.planetMapSize = planetMapSize;
        this.planetMapObstaclesAndPlayers = planetMapObtaclesAndPlayers;
    }

    final class Player{
        public final Position position;
        public final MarsRover marsRover;

        Player(Position position) {
            this.position = position;
            this.marsRover = new MoveMarsRover();
        }
    }

    @Override
    public Position roverPosition(String playerName) {
        return null;
    }

//    @Override
//    public Set<Position> radar(String playerName) {
//        return null;
//    }
//
//    @Override
//    public int laserRange(String playerName) {
//        return 0;
//    }
//
//    @Override
//    public Position move(String playerName, String command) {
//        return null;
//    }
//
//    @Override
//    public Boolean alive(String playerName) {
//        return null;
//    }

    @Override
    public PlayerController addPlayer(String playerName) {
        PlayerController controller = new PlayerControllerImpl(this,playerName);

        boolean stop = false;
        int xPos = 0;
        int yPos = 0;
        if(players.containsKey(playerName)){
            return null;
        }
        while(!stop){
            stop = true;
            xPos = (int) (Math.random()*this.planetMapSize+1-this.planetMapSize/2);
            yPos = (int) (Math.random()*this.planetMapSize+1-this.planetMapSize/2);
            for (Position position : this.planetMapObstaclesAndPlayers){
                if (position.getX() == xPos && position.getY() == yPos){
                    stop = false;
                    break;
                }
            }
        }
        Position position = Position.of(xPos,yPos,Direction.NORTH);
        this.planetMapObstaclesAndPlayers.add(position);
        Player player = new Player(position);
        this.players.put(playerName,player);
        return controller.startNew(this,playerName);
    }


}
