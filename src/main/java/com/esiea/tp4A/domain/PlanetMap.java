package com.esiea.tp4A.domain;

import java.util.Set;

public interface PlanetMap {

    Set<Position> obstaclePositions();

    void deleteObstacle(Position obstaclePosition);

    final class Map implements PlanetMap {

        private final Set<Position> obstacles;

        public Map(Set<Position> obstacles) {
            this.obstacles = obstacles;
        }

        @Override
        public Set<Position> obstaclePositions() {
            return this.obstacles;
        }

        @Override
        public void deleteObstacle(Position obstaclePosition) {
            obstacles.removeIf(position -> position.getX() == obstaclePosition.getX() && position.getY() == obstaclePosition.getY());
        }

    }
}
