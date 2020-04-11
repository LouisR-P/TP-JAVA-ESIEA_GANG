package com.esiea.tp4A.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

class MoveMarsRoverTest {

    private Position position;
    private int laserRange;
    private PlanetMap planetMap;
    private Set<Position> obstacles = new HashSet<>();

    @Test
    void notMoving() throws Exception {
        MarsRover marsRover = new MoveMarsRover(position, laserRange, planetMap).initialize(Position.of(0,0,Direction.NORTH));
        Position newPosition = marsRover.move("");
        Assertions.assertThat(newPosition).isEqualTo(Position.of(0, 0, Direction.NORTH));
    }

    @Test
    void MovingForwardRover() throws Exception {
        MarsRover marsRover = new MoveMarsRover(position, laserRange, planetMap).initialize(Position.of(0,0,Direction.NORTH));
        Position newPosition = marsRover.move("f");
        Assertions.assertThat(newPosition).isEqualTo(Position.of(0, 1, Direction.NORTH));
    }

    @Test
    void MovingForwardRoverGeneral() throws Exception {
        MarsRover marsRover = new MoveMarsRover(position, laserRange, planetMap).initialize(Position.of(0,0,Direction.NORTH));
        Position newPosition = marsRover.move("f");
        Assertions.assertThat(newPosition).isEqualTo(Position.of(0, 1, Direction.NORTH));
    }

    @Test
    void MovingBackwardRover() throws Exception {
        MarsRover marsRover = new MoveMarsRover(position, laserRange, planetMap).initialize(Position.of(0,0,Direction.NORTH));
        Position newPosition = marsRover.move("b");
        Assertions.assertThat(newPosition).isEqualTo(Position.of(0, -1, Direction.NORTH));
    }

    @Test
    void TurningRightRover() throws Exception {
        MarsRover marsRover = new MoveMarsRover(position, laserRange, planetMap).initialize(Position.of(0,0,Direction.NORTH));
        Position newPosition = marsRover.move("r");
        Assertions.assertThat(newPosition).isEqualTo(Position.of(0, 0, Direction.EAST));
    }

    @Test
    void TurningLeftRover() throws Exception {
        MarsRover marsRover = new MoveMarsRover(position, laserRange, planetMap).initialize(Position.of(0,0,Direction.NORTH));
        Position newPosition = marsRover.move("l");
        Assertions.assertThat(newPosition).isEqualTo(Position.of(0, 0, Direction.WEST));
    }

    @Test
    void complexMovesFromDifferentOrigin() throws Exception {
        MarsRover marsRover = new MoveMarsRover(position, laserRange, planetMap).initialize(Position.of(4,5,Direction.WEST));
        Position newPosition = marsRover.move("ff");
        Assertions.assertThat(newPosition).isEqualTo(Position.of(2, 5, Direction.WEST));
    }

    @Test
    void MajCommand() throws Exception {
        MarsRover marsRover = new MoveMarsRover(position, laserRange, planetMap).initialize(Position.of(0,0,Direction.NORTH));
        Position newPosition = marsRover.move("F");
        Assertions.assertThat(newPosition).isEqualTo(Position.of(0, 1, Direction.NORTH));
    }

    @Test
    void WrongCommand() {
        MarsRover marsRover = new MoveMarsRover(position, laserRange, planetMap).initialize(Position.of(0,0,Direction.NORTH));
        Assertions.assertThatExceptionOfType(InvalidCommandException.class)
            .isThrownBy(() -> marsRover.move("o"));
    }

    @Test
    void SuccessionMovingRover() throws Exception {
        MarsRover marsRover = new MoveMarsRover(position, laserRange, planetMap).initialize(Position.of(0,0,Direction.NORTH));
        Position newPosition = marsRover.move("fflb");
        Assertions.assertThat(newPosition).isEqualTo(Position.of(1, 2, Direction.WEST));
    }

    @Test
    void MovingRoverOnSphericalMap() throws Exception {
        MarsRover marsRover = new MoveMarsRover(position, laserRange, planetMap).initialize(Position.of(0,50,Direction.NORTH));
        Position newPosition = marsRover.move("f");
        Assertions.assertThat(newPosition).isEqualTo(Position.of(0, -49, Direction.NORTH));
    }

    @Test
    void MovingRoverOnSphericalMapWithObstacles() throws Exception{
        MarsRover marsRover = new MoveMarsRover(position, laserRange, planetMap).initialize(Position.of(0,0,Direction.NORTH));
        PlanetMap planetMap = new PlanetMap.Map(obstacles);
        obstacles.add(Position.of(0,1,Direction.NORTH));
        MarsRover newMarsRover = marsRover.updateMap(planetMap);
        Position newPosition = newMarsRover.move("fflb");
        Assertions.assertThat(newPosition).isEqualTo(Position.of(1, 0, Direction.WEST));
    }

    @Test
    void MovingRoverWithObstaclesAndShootingWithLaserRangeEqualsTo2() throws Exception{
        MarsRover marsRover = new MoveMarsRover(position, laserRange, planetMap).initialize(Position.of(0,0,Direction.NORTH));
        PlanetMap planetMap = new PlanetMap.Map(obstacles);
        obstacles.add(Position.of(0,2,Direction.NORTH));
        MarsRover updateMarsRover = marsRover.updateMap(planetMap);
        MarsRover newsMarsRover1 = updateMarsRover.configureLaserRange(2);
        Position newPosition = newsMarsRover1.move("sff");
        Assertions.assertThat(newPosition).isEqualTo(Position.of(0, 2, Direction.NORTH));
    }

}
