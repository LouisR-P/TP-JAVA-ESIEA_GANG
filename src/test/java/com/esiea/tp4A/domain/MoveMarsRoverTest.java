package com.esiea.tp4A.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.HashSet;
import java.util.Set;

class MoveMarsRoverTest {

    private int laserRange;
    private PlanetMap planetMap;
    private Set<Position> obstacles = new HashSet<>();
    private MarsRover marsRover = new MoveMarsRover(laserRange,planetMap);

    @ParameterizedTest
    @CsvSource({
        "'ff', 3, -3, WEST",
        "lbblffr, 7, -1, SOUTH"
    })
    void complex_moves_from_different_origin(String command, int expectedX, int expectedY, Direction expectedDirection) {
        marsRover.initialize(Position.of(5, -3, Direction.WEST));
        Position newPosition = marsRover.move(command);
        Assertions.assertThat(newPosition).as("new position after receiving command '" + command + "'").isEqualTo(Position.of(expectedX, expectedY, expectedDirection));
    }

    @Test
    void notMoving(){
        marsRover.initialize(Position.of(0, 0, Direction.NORTH));
        Position newPosition = marsRover.move("");
        Assertions.assertThat(newPosition).isEqualTo(Position.of(0, 0, Direction.NORTH));
    }

    @Test
    void MovingForwardRover(){
        marsRover.initialize(Position.of(0, 0, Direction.NORTH));
        Position newPosition = marsRover.move("f");
        Assertions.assertThat(newPosition).isEqualTo(Position.of(0, 1, Direction.NORTH));
    }

    @Test
    void MovingForwardRoverGeneral(){
        marsRover.initialize(Position.of(0, 0, Direction.NORTH));
        Position newPosition = marsRover.move("f");
        Assertions.assertThat(newPosition).isEqualTo(Position.of(0, 1, Direction.NORTH));
    }

    @Test
    void MovingBackwardRover(){
        marsRover.initialize(Position.of(0, 0, Direction.NORTH));
        Position newPosition = marsRover.move("b");
        Assertions.assertThat(newPosition).isEqualTo(Position.of(0, -1, Direction.NORTH));
    }

    @Test
    void TurningRightRover(){
        marsRover.initialize(Position.of(0, 0, Direction.NORTH));
        Position newPosition = marsRover.move("r");
        Assertions.assertThat(newPosition).isEqualTo(Position.of(0, 0, Direction.EAST));
    }

    @Test
    void TurningRightRoverFromWestDirection(){
        marsRover.initialize(Position.of(0, 0, Direction.WEST));
        Position newPosition = marsRover.move("r");
        Assertions.assertThat(newPosition).isEqualTo(Position.of(0, 0, Direction.NORTH));
    }

    @Test
    void TurningLeftRover(){
        marsRover.initialize(Position.of(0, 0, Direction.NORTH));
        Position newPosition = marsRover.move("l");
        Assertions.assertThat(newPosition).isEqualTo(Position.of(0, 0, Direction.WEST));
    }

    @Test
    void TurningLeftRoverFromEastDirection(){
        marsRover.initialize(Position.of(0, 0, Direction.EAST));
        Position newPosition = marsRover.move("l");
        Assertions.assertThat(newPosition).isEqualTo(Position.of(0, 0, Direction.NORTH));
    }

    @Test
    void complexMovesFromDifferentOrigin(){
        marsRover.initialize(Position.of(4, 4, Direction.WEST));
        Position newPosition = marsRover.move("ff");
        Assertions.assertThat(newPosition).isEqualTo(Position.of(2, 4, Direction.WEST));
    }

    @Test
    void MajCommand(){
        marsRover.initialize(Position.of(0, 0, Direction.NORTH));
        Position newPosition = marsRover.move("F");
        Assertions.assertThat(newPosition).isEqualTo(Position.of(0, 1, Direction.NORTH));
    }

    @Test
    void TestWrongCommand(){
        marsRover.initialize(Position.of(0, 0, Direction.NORTH));
        Position newPosition = marsRover.move("aff");
        Assertions.assertThat(newPosition).isEqualTo(Position.of(0,0,Direction.NORTH));
    }

    @Test
    void SuccessionMovingRover(){
        marsRover.initialize(Position.of(0, 0, Direction.NORTH));
        Position newPosition = marsRover.move("fflb");
        Assertions.assertThat(newPosition).isEqualTo(Position.of(1, 2, Direction.WEST));
    }

    @Test
    void MovingRoverOnSphericalMap(){
        marsRover.initialize(Position.of(0, 50, Direction.NORTH));
        Position newPosition = marsRover.move("f");
        Assertions.assertThat(newPosition).isEqualTo(Position.of(0, -49, Direction.NORTH));
    }

    @Test
    void MovingRoverOnSphericalMapOnTheOtherEdge(){
        marsRover.initialize(Position.of(0, -49, Direction.NORTH));
        Position newPosition = marsRover.move("b");
        Assertions.assertThat(newPosition).isEqualTo(Position.of(0, 50, Direction.NORTH));
    }

    @Test
    void MovingRoverOnSphericalMapWithObstacles(){
        marsRover.initialize(Position.of(0, 0, Direction.NORTH));
        PlanetMap planetMap = new PlanetMap.Map(obstacles);
        obstacles.add(Position.of(0,1,Direction.NORTH));
        MarsRover newMarsRover = marsRover.updateMap(planetMap);
        Position newPosition = newMarsRover.move("fflb");
        Assertions.assertThat(newPosition).isEqualTo(Position.of(1, 0, Direction.WEST));
    }

    @Test
    void MovingRoverWithObstaclesAndShootingWithLaserRangeEqualsTo2(){
        marsRover.initialize(Position.of(0, 0, Direction.NORTH));
        PlanetMap planetMap = new PlanetMap.Map(obstacles);
        obstacles.add(Position.of(0,2,Direction.NORTH));
        MarsRover updateMarsRover = marsRover.updateMap(planetMap);
        MarsRover newsMarsRover1 = updateMarsRover.configureLaserRange(2);
        Position newPosition = newsMarsRover1.move("sff");
        Assertions.assertThat(newPosition).isEqualTo(Position.of(0, 2, Direction.NORTH));
    }

}
