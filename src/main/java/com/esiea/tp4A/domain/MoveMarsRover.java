package com.esiea.tp4A.domain;

public class MoveMarsRover implements MarsRover {

    private final Position position;
    private final PlanetMap planetMap;


    public MoveMarsRover(int x, int y, Direction direction, PlanetMap planetMap) {
        position = Position.of(x, y, direction);
        this.planetMap = planetMap;
    }

    @Override
    public Position move(String command) {
        Position pos = position;
        Direction direction = pos.getDirection();
        for (char instruction : (command.toLowerCase()).toCharArray()) {
            switch (instruction) {
                case 'l':
                    direction = direction.left(); break;
                case 'r':
                    direction = direction.right(); break;
                case 'f':
                    pos = pos.movingPosition(pos.getX(), pos.getY(), direction, planetMap); break;
                case 'b':
                    pos = pos.movingPosition(pos.getX(), pos.getY(), direction.getOppositeDirection(), planetMap); break;
                default:
                    throw new InvalidCommandException("Commande " + command + " inconnue");
            }
        }
        return new Position.FixedPosition(pos.getX(), pos.getY(), direction);
    }
}
