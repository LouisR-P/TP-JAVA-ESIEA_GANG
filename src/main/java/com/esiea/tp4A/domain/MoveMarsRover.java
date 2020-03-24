package com.esiea.tp4A.domain;

public class MoveMarsRover implements MarsRover {

    private final Position position;

    public MoveMarsRover(int x, int y, Direction direction) {
        position = Position.of(x, y, direction);
    }

    @Override
    public Position move(String command) {
        int xPosition = position.getX();
        int yPosition = position.getY();
        Direction direction = position.getDirection();

        command = command.toLowerCase(); // Sensibilité à la casse

        for (int i = 0; i < command.length(); i++) {
            if (command.charAt(i) == 'l') {
                direction = direction.left();
            }
            else if (command.charAt(i) == 'r') {
                direction = direction.right();
            }
            else if ((command.charAt(i) == 'f') || (command.charAt(i) == 'b')) {
                switch (direction) {
                    case NORTH:
                        yPosition = (command.charAt(i) == 'f') ? yPosition + 1 : yPosition - 1;
                        break;
                    case EAST:
                        xPosition = (command.charAt(i) == 'f') ? xPosition + 1 : xPosition - 1;
                        break;
                    case SOUTH:
                        yPosition = (command.charAt(i) == 'f') ? yPosition - 1 : yPosition + 1;
                        break;
                    case WEST:
                        xPosition = (command.charAt(i) == 'f') ? xPosition - 1 : xPosition + 1;
                        break;
                }
            }
            else {
                throw new InvalidCommandException("Commande " + command + " inconnue");
            }
        }
        Position.FixedPosition fixedPosition = new Position.FixedPosition(xPosition, yPosition, direction);
        return fixedPosition;
    }
}
