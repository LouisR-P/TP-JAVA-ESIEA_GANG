package com.esiea.tp4A.domain;

import java.util.Iterator;
import java.util.concurrent.atomic.AtomicReference;

public class MoveMarsRover implements MarsRover {

    private final AtomicReference<Position> positionAtomicReference;
    private final AtomicReference<Integer> laserRangeAtomicReference;
    private final AtomicReference<PlanetMap> planetMapAtomicReference;

    public MoveMarsRover() {
        this.laserRangeAtomicReference = new AtomicReference<>(0);
        this.planetMapAtomicReference = new AtomicReference<>(null);
        this.positionAtomicReference = new AtomicReference<>(Position.of(0,0,Direction.NORTH));
    }

    @Override
    public MarsRover initialize(Position position) {
        this.positionAtomicReference.getAndSet(position);
        return new MoveMarsRover();
    }

    @Override
    public MarsRover updateMap(PlanetMap planetMap) {
        this.planetMapAtomicReference.getAndSet(planetMap);
        return new MoveMarsRover();
    }

    @Override
    public MarsRover configureLaserRange(int range) {
        this.laserRangeAtomicReference.getAndSet(range);
        return new MoveMarsRover();
    }

    @Override
    public Position move(String command) {
        Position pos = positionAtomicReference.get();
        Direction direction = pos.getDirection();
        for (char instruction : (command.toLowerCase()).toCharArray()) {
            switch (instruction) {
                case 'l':
                    direction = direction.left(); break;
                case 'r':
                    direction = direction.right(); break;
                case 'f':
                    pos = pos.movingPosition(pos.getX(), pos.getY(), direction, planetMapAtomicReference.get()); break;
                case 'b':
                    pos = pos.movingPosition(pos.getX(), pos.getY(), direction.getOppositeDirection(), planetMapAtomicReference.get()); break;
                case 's':
                    this.laserShot(); break;
                default:
                    break;
            }
        } return new Position.FixedPosition(pos.getX(), pos.getY(), direction);
    }

    private Position laserShot() {
        Position previousPosition = this.positionAtomicReference.get();
        Position tempPosition;
        String command = "f";
        for (int i = 1; i<=laserRangeAtomicReference.get(); i++){
            tempPosition = this.move(command);
            if(tempPosition.equals(previousPosition)){
                for (Iterator<Position> iterator = this.planetMapAtomicReference.get().obstaclePositions().iterator(); iterator.hasNext();){
                    Position position = iterator.next();
                    this.planetMapAtomicReference.get().obstaclePositions().removeIf(tempPos -> tempPos.getX() == position.getX() && tempPos.getY() == position.getY());
                } break;
            } previousPosition = tempPosition;
            command = command.concat("f");
        } return previousPosition;
    }

}
