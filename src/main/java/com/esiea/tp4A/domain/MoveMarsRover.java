package com.esiea.tp4A.domain;

public class MoveMarsRover implements MarsRover {

    private final Position position;

    public MoveMarsRover(int x, int y, Direction direction) {
        position = Position.of(x, y, direction);
    }

    @Override
    public Position instructions(String command) {
        Position pos = position;
        Direction direction = pos.getDirection();
        for (char instruction : (command.toLowerCase()).toCharArray()) {
            switch (instruction) {
                case 'l':
                    direction = direction.left(); break;
                case 'r':
                    direction = direction.right(); break;
                case 'f':
                    pos = pos.move(pos.getX(), pos.getY(), direction); break;
                case 'b':
                    pos = pos.move(pos.getX(), pos.getY(), direction.getOppositeDirection()); break;
                default:
                    throw new InvalidCommandException("Commande " + command + " inconnue");
            }
        }
        return new Position.FixedPosition(pos.getX(), pos.getY(), direction);
    }
}
