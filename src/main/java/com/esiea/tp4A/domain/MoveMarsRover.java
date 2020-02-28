package com.esiea.tp4A.domain;

public class MoveMarsRover implements MarsRover {


    Position position;

    public MoveMarsRover(int x, int y, Direction direction) {
        position = Position.of(x, y, direction);
    }

    @Override
    public Position move(String command) {

        int xPosition = position.getX();
        int yPosition = position.getY();
        Direction direction = position.getDirection();

        final String separateur = ",";

        // Suppression du préfixe et du suffixe de la chaîne de caractères entrée
        command = command.substring(1);
        command = command.substring(0,command.length()-1);

        String instructions[] = command.split(separateur);

        for (int i = 0; i < instructions.length; i++) {
            //System.out.println(instructions[i]);

            instructions[i] = instructions[i].toLowerCase();
            //System.out.println(command);
            if (instructions[i].equals("f") || instructions[i].equals("b") || instructions[i].equals("l") || instructions[i].toLowerCase().equals("r") || command.startsWith("[") && command.endsWith("]")) {

                //System.out.println(command);
                switch (direction) {

                    case NORTH:
                        if (instructions[i].equals("f")) {
                            yPosition = yPosition + 1;

                        } else if (instructions[i].equals("b")) {
                            yPosition = yPosition - 1;

                        } else if (instructions[i].equals("l")) {
                            direction = Direction.WEST;

                        } else if (instructions[i].equals("r")) {
                            direction = Direction.EAST;

                        }
                        break;

                    case EAST:
                        if (instructions[i].equals("f")) {
                            xPosition = xPosition + 1;


                        } else if (instructions[i].equals("b")) {
                            xPosition = xPosition - 1;


                        } else if (instructions[i].equals("l")) {
                            direction = Direction.NORTH;


                        } else if (instructions[i].equals("r")) {
                            direction = Direction.SOUTH;

                        }
                        break;


                    case SOUTH:
                        if (instructions[i].equals("f")) {
                            yPosition = yPosition - 1;


                        } else if (instructions[i].equals("b")) {
                            yPosition = yPosition + 1;


                        } else if (instructions[i].equals("l")) {
                            direction = Direction.EAST;


                        } else if (instructions[i].equals("r")) {
                            direction = Direction.WEST;

                        }
                        break;

                    case WEST:
                        if (instructions[i].equals("f")) {
                            xPosition = xPosition - 1;

                        } else if (instructions[i].equals("b")) {
                            xPosition = xPosition + 1;

                        } else if (instructions[i].equals("l")) {
                            direction = Direction.SOUTH;


                        } else if (instructions[i].equals("r")) {
                            direction = Direction.NORTH;

                        }
                        break;
                }
            } else {
                throw new InvalidCommandException("Commande " + command + " inconnue");
            }
        }

        position = Position.of(xPosition, yPosition, direction);
        Position.FixedPosition fixedPosition = new Position.FixedPosition(position.getX(), position.getY(), position.getDirection());

        return fixedPosition;
    }
}
