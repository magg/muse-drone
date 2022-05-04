package com.magg.drone.model;

import lombok.Data;

import static com.magg.drone.utils.Constants.DIRECTION_STR;

@Data
public class Position
{
    private int x;
    private int y;
    private Direction direction;


    @Override
    public String toString() {
        return "("+x+", "+y+") "+ DIRECTION_STR + direction.getName();
    }
}
