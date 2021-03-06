package com.esiea.tp4A.domain;

import java.util.Objects;

public interface Position {

    int getX();
    int getY();
    Direction getDirection();
    Position movingPosition(int x, int y, Direction direction, PlanetMap planetMap);

    static Position of(int x, int y, Direction direction) {
        return new FixedPosition(x, y, direction);
    }

    final class FixedPosition implements Position {

        private final int x;
        private final int y;
        private final Direction direction;

        public FixedPosition(int x, int y, Direction direction) {
            this.x = x;
            this.y = y;
            this.direction = direction;
        }

        @Override
        public int getX() {
            return x;
        }

        @Override
        public int getY() {
            return y;
        }

        @Override
        public Direction getDirection() {
            return direction;
        }

        @Override
        public Position movingPosition(int xPosition, int yPosition, Direction direction, PlanetMap planetMap) {
            switch (direction) {
                case NORTH:
                    yPosition = forwardPosition(yPosition); break;
                case EAST:
                    xPosition = forwardPosition(xPosition); break;
                case SOUTH:
                    yPosition = backwardPosition(yPosition); break;
                case WEST:
                    xPosition = backwardPosition(xPosition); break;
            } if (!containsObstacle(xPosition,yPosition,planetMap)){
                return Position.of(xPosition, yPosition, direction);
            } else {
                return Position.of(getX(), getY(), getDirection());
            }
        }

        private boolean containsObstacle(int xPosition, int yPosition, PlanetMap planetMap) {
            if (!(planetMap == null)) {
                for (Position obstacle : planetMap.obstaclePositions()) {
                    if (obstacle.getX() == xPosition && obstacle.getY() == yPosition) {
                        return true;
                    }
                }
            } return false;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            FixedPosition that = (FixedPosition) o;
            return x == that.x &&
                y == that.y &&
                direction == that.direction;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y, direction);
        }

        public int forwardPosition(int coordinate) {
            if (coordinate < 50) {
                return coordinate + 1;
            } else {
                return -49;
            }
        }

        public int backwardPosition(int coordinate) {
            if (coordinate > -49) {
                return coordinate - 1;
            } else {
                return 50;
            }
        }
    }
}
