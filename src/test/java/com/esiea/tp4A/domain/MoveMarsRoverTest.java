package com.esiea.tp4A.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

class MoveMarsRoverTest {

    PlanetMap planetMap;

    @Test
    void notMoving() throws Exception {
        MarsRover marsRover = new MoveMarsRover(0,0, Direction.NORTH, planetMap);
        Position newPosition = marsRover.move("");
        Assertions.assertThat(newPosition).isEqualTo(Position.of(0, 0, Direction.NORTH));
    }

    @Test
    void MovingForwardRover() throws Exception {
        MarsRover marsRover = new MoveMarsRover(0, 0, Direction.NORTH, planetMap);
        Position newPosition = marsRover.move("f");
        Assertions.assertThat(newPosition).isEqualTo(Position.of(0, 1, Direction.NORTH));
    }

    @Test
    void MovingBackwardRover() throws Exception {
        MarsRover marsRover = new MoveMarsRover(0, 0, Direction.NORTH, planetMap);
        Position newPosition = marsRover.move("b");
        Assertions.assertThat(newPosition).isEqualTo(Position.of(0, -1, Direction.NORTH));
    }

    @Test
    void TurningRightRover() throws Exception {
        MarsRover marsRover = new MoveMarsRover(0, 0, Direction.NORTH, planetMap);
        Position newPosition = marsRover.move("r");
        Assertions.assertThat(newPosition).isEqualTo(Position.of(0, 0, Direction.EAST));
    }

    @Test
    void TurningLeftRover() throws Exception {
        MarsRover marsRover = new MoveMarsRover(0, 0, Direction.NORTH, planetMap);
        Position newPosition = marsRover.move("l");
        Assertions.assertThat(newPosition).isEqualTo(Position.of(0, 0, Direction.WEST));
    }

    @Test
    void complexMovesFromDifferentOrigin() throws Exception {
        MarsRover marsRover = new MoveMarsRover(4, 5, Direction.WEST, planetMap);
        Position newPosition = marsRover.move("ff");
        Assertions.assertThat(newPosition).isEqualTo(Position.of(2, 5, Direction.WEST));
    }

    @Test
    void MajCommand() throws Exception {
        MarsRover marsRover = new MoveMarsRover(0, 0, Direction.NORTH, planetMap);
        Position newPosition = marsRover.move("F");
        Assertions.assertThat(newPosition).isEqualTo(Position.of(0, 1, Direction.NORTH));
    }

    @Test
    void WrongCommand() {
        MarsRover marsRover = new MoveMarsRover(0, 0, Direction.NORTH, planetMap);
        Assertions.assertThatExceptionOfType(InvalidCommandException.class)
            .isThrownBy(() -> marsRover.move("o"));
    }

    @Test
    void SuccessionMovingRover() throws Exception {
        MarsRover marsRover = new MoveMarsRover(0, 0, Direction.NORTH, planetMap);
        Position newPosition = marsRover.move("fflb");
        Assertions.assertThat(newPosition).isEqualTo(Position.of(1, 2, Direction.WEST));
    }

    @Test
    void MovingRoverOnSphericalMap() throws Exception {
        MarsRover marsRover = new MoveMarsRover(-48, 50, Direction.NORTH, planetMap);
        Position newPosition = marsRover.move("fflfff");
        Assertions.assertThat(newPosition).isEqualTo(Position.of(49, -48, Direction.WEST));
    }

    @Test
    void MovingRoverOnSphericalMapWithObstacles() throws Exception{
        Set<Position> obstacles = new HashSet<>();
        obstacles.add(Position.of(0,1,Direction.NORTH));
        PlanetMap planetMap = new PlanetMap.Map(obstacles);
        MarsRover marsRover = new MoveMarsRover(0,0,Direction.NORTH, planetMap);
        Position newPosition = marsRover.move("fflb");
        Assertions.assertThat(newPosition).isEqualTo(Position.of(1, 0, Direction.WEST));
    }

}
