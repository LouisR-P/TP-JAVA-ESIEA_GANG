package com.esiea.tp4A.domain;

public enum Direction {

    NORTH, EAST, SOUTH, WEST;

    public Direction left() {
        int leftOrdinal = ordinal()-1;
        if (leftOrdinal == -1){
            return WEST;
        }
        else {
            return Direction.values()[leftOrdinal];
        }
    }

    public Direction right() {
        int rightOrdinal = ordinal()+1;
        if (rightOrdinal == 3){
            return NORTH;
        }
        else {
            return Direction.values()[rightOrdinal];
        }
    }

    public Direction getOppositeDirection(){
        return values()[(this.ordinal()+2)%4];
    }
}
