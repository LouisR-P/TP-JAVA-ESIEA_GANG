package com.esiea.tp4A.domain;

import java.util.Set;

public interface PlanetMap {

    Set<Position> obstaclePositions();

    final class Map implements PlanetMap {

        private Set<Position> obstacles;

        public Map(Set<Position> obstacles) {
            this.obstacles = obstacles;
        }

        @Override
        public Set<Position> obstaclePositions() {
            return this.obstacles;
        }

    }
}
