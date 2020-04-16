package com.esiea.tp4A.domain;

import java.util.Iterator;
import java.util.concurrent.atomic.AtomicReference;

public class MoveMarsRover implements MarsRover {

    private final AtomicReference<Position> atomicReference;
    private final int laserRange;
    private final PlanetMap planetMap;

    public MoveMarsRover(int laserRange, PlanetMap planetMap) {
        this.laserRange = laserRange;
        this.planetMap = planetMap;
        this.atomicReference = new AtomicReference<>(Position.of(0,0,Direction.NORTH));
    }

    @Override
    public MarsRover initialize(Position position) {
        this.atomicReference.getAndSet(position);
        return new MoveMarsRover(this.laserRange, this.planetMap);
    }

    @Override
    public MarsRover updateMap(PlanetMap planetMap) {
        return new MoveMarsRover(this.laserRange, planetMap);
    }

    @Override
    public MarsRover configureLaserRange(int range) {
        return new MoveMarsRover(range, planetMap);
    }

    @Override
    public Position move(String command) {
        Position pos = atomicReference.get();
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
                    return this.atomicReference.get();
            }
        }
        return new Position.FixedPosition(pos.getX(), pos.getY(), direction);
    }

    private Position laserShot() {
        Position previousPosition = this.atomicReference.get();
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
