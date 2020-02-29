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

        position = Position.of(xPosition, yPosition, direction);


        // Suppression du préfixe et du suffixe de la chaîne de caractères saisie en entrée
        command = command.substring(1);
        command = command.substring(0,command.length()-1);
        // Suppression des espaces de la chaîne de caractères saisie en entrée
        command = command.replaceAll("\\s", "");
        // Définition du séparateur et séparation des instructions en une unique instruction
        final String separateur = ",";
        String instructions[] = command.split(separateur);


        for (int i = 0; i < instructions.length; i++) {

            instructions[i] = instructions[i].toLowerCase();

            if (instructions[i].equals("l")) {
                direction = direction.left();
            }

            else if (instructions[i].equals("r")) {
                direction = direction.right();
            }

            // CONDIITON A AJOUTER  || command.startsWith("[") && command.endsWith("]"))
            else if (instructions[i].equals("f") || instructions[i].equals("b")) {

                switch (direction) {

                    case NORTH:
                        if (instructions[i].equals("f")) {
                            yPosition = yPosition + 1;
                        } else if (instructions[i].equals("b")) {
                            yPosition = yPosition - 1;
                        } break;

                    case EAST:
                        if (instructions[i].equals("f")) {
                            xPosition = xPosition + 1;
                        } else if (instructions[i].equals("b")) {
                            xPosition = xPosition - 1;
                        } break;

                    case SOUTH:
                        if (instructions[i].equals("f")) {
                            yPosition = yPosition - 1;
                        } else if (instructions[i].equals("b")) {
                            yPosition = yPosition + 1;
                        } break;

                    case WEST:
                        if (instructions[i].equals("f")) {
                            xPosition = xPosition - 1;
                        } else if (instructions[i].equals("b")) {
                            xPosition = xPosition + 1;
                        } break;

                }
            }

            else {
                throw new InvalidCommandException("Commande " + command + " inconnue");
            }

        }

        position = Position.of(xPosition, yPosition, direction);
        Position.FixedPosition fixedPosition = new Position.FixedPosition(position.getX(), position.getY(), position.getDirection());
        return fixedPosition;

    }
}
