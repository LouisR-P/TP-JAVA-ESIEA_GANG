package com.esiea.tp4A.domain;

import java.util.Iterator;

public class MoveMarsRover implements MarsRover {

    private final Position position;
    private final int laserRange;
    private final PlanetMap planetMap;

    public MoveMarsRover(Position position, int laserRange, PlanetMap planetMap) {
        this.position = position;
        this.laserRange = laserRange;
        this.planetMap = planetMap;
    }

    @Override
    public MarsRover initialize(Position position) {
        return new MoveMarsRover(position, this.laserRange, planetMap);
    }

    @Override
    public MarsRover updateMap(PlanetMap planetMap) {
        return new MoveMarsRover(this.position, this.laserRange, planetMap);
    }

    @Override
    public MarsRover configureLaserRange(int range) {
        return new MoveMarsRover(this.position, range, planetMap);
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
                case 's':
                    this.laserShot();
                    break;
                default:
                    throw new InvalidCommandException("Commande " + command + " inconnue");
            }
        }
        return new Position.FixedPosition(pos.getX(), pos.getY(), direction);
    }

    private Position laserShot() {
        Position previousPosition = this.position;
        Position tempPosition;
        for (int i=1; i<=laserRange; i++){
            tempPosition = this.move("f");
            if(tempPosition.equals(previousPosition)){
                for (Iterator<Position> iterator = this.planetMap.obstaclePositions().iterator(); iterator.hasNext();){
                    Position position = iterator.next();
                    this.planetMap.deleteObstacle(position);
                }
                break;
            } previousPosition = tempPosition;
        }
        return previousPosition;
    }

}
