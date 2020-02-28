package com.esiea.tp4A.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class MoveMarsRoverTest {
    @Test
    void MovingForwardRover() throws Exception {
        MarsRover marsRover = new MoveMarsRover(0, 0, Direction.NORTH);
        Position newPosition = marsRover.move("[f]");
        Assertions.assertThat(newPosition).isEqualTo(Position.of(0, 1, Direction.NORTH));
    }

    @Test
    void MovingBackwardRover() throws Exception {
        MarsRover marsRover = new MoveMarsRover(0, 0, Direction.NORTH);
        Position newPosition = marsRover.move("[b]");
        Assertions.assertThat(newPosition).isEqualTo(Position.of(0, -1, Direction.NORTH));
    }

    @Test
    void TurningRightRover() throws Exception {
        MarsRover marsRover = new MoveMarsRover(0, 0, Direction.NORTH);
        Position newPosition = marsRover.move("[r]");
        Assertions.assertThat(newPosition).isEqualTo(Position.of(0, 0, Direction.EAST));
    }

    @Test
    void TurningLeftRover() throws Exception {
        MarsRover marsRover = new MoveMarsRover(0, 0, Direction.NORTH);
        Position newPosition = marsRover.move("[l]");
        Assertions.assertThat(newPosition).isEqualTo(Position.of(0, 0, Direction.WEST));
    }

    @Test
    void MajCommand() throws Exception {
        MarsRover marsRover = new MoveMarsRover(0, 0, Direction.NORTH);
        Position newPosition = marsRover.move("[F]");
        Assertions.assertThat(newPosition).isEqualTo(Position.of(0, 1, Direction.NORTH));
    }

    @Test
    void WrongCommand() {
        MarsRover marsRover = new MoveMarsRover(0, 0, Direction.NORTH);
        Assertions.assertThatExceptionOfType(InvalidCommandException.class)
            .isThrownBy(() -> marsRover.move("[o]"));
    }


    @Test
    void SuccessionMovingRover() throws Exception {
        MarsRover marsRover = new MoveMarsRover(0, 0, Direction.NORTH);
        Position newPosition = marsRover.move("[f,f,l,b]");
        Assertions.assertThat(newPosition).isEqualTo(Position.of(1, 2, Direction.WEST));
    }
}
