package com.magg.drone.model;

import com.magg.drone.utils.Constants;
import lombok.Getter;

import static com.magg.drone.utils.Constants.EAST_STR;
import static com.magg.drone.utils.Constants.NORTH_STR;
import static com.magg.drone.utils.Constants.SOUTH_STR;
import static com.magg.drone.utils.Constants.WEST_STR;

@Getter
public enum Direction {
    NORTH(NORTH_STR),
    EAST(EAST_STR),
    SOUTH(SOUTH_STR),
    WEST(WEST_STR);

    private final String name;

    Direction(String name) {
        this.name = name;
    }
}
