package com.esiea.tp4A.domain;

public interface MarsRover {

    default MarsRover initialize(Position position) {
        return this;
    }

    default MarsRover updateMap(PlanetMap map) {
        return this;
    }

    default MarsRover configureLaserRange(int range) {
        return this;
    }

    default Position instructions(String command) throws Exception {
        return Position.of(0, 0, Direction.NORTH);
    }
}
