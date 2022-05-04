package com.magg.drone.model;

import com.magg.drone.exception.NumberOfBlocksExceededException;

import static com.magg.drone.config.DroneDeliveryConfig.NUMBER_OF_BLOCKS;

public class Drone
{
    private Position position;

    public Drone()
    {
        this.position = new Position();
        this.position.setDirection(Direction.NORTH);
    }

    public void moveForward() {
        if (position.getDirection() == Direction.NORTH) {
            position.setY(position.getY() + 1);
        } else if (position.getDirection() == Direction.EAST) {
            position.setX(position.getX() + 1);
        } else if(position.getDirection() == Direction.WEST){
            position.setX(position.getX() - 1);
        } else if (position.getDirection() == Direction.SOUTH){
            position.setY(position.getY() - 1);
        }

        boolean result = validate(position);

        if (!result) {
            throw new NumberOfBlocksExceededException("Max blocks " + NUMBER_OF_BLOCKS +" position " + position.toString());
        }
    }

    public void turnLeft() {
        if (position.getDirection() == Direction.NORTH) {
            position.setDirection(Direction.WEST);
        } else if (position.getDirection() == Direction.EAST) {
            position.setDirection(Direction.NORTH);
        } else if (position.getDirection() == Direction.WEST) {
            position.setDirection(Direction.SOUTH);
        } else if (position.getDirection() == Direction.SOUTH) {
            position.setDirection(Direction.EAST);
        }
    }

    public void turnRight() {
        if (position.getDirection() == Direction.NORTH) {
            position.setDirection(Direction.EAST);
        } else if (position.getDirection() == Direction.EAST) {
            position.setDirection(Direction.SOUTH);
        } else if (position.getDirection() == Direction.WEST) {
            position.setDirection(Direction.NORTH);
        } else if (position.getDirection() == Direction.SOUTH) {
            position.setDirection(Direction.WEST);
        }
    }

    public Position getPosition() {
        return position;
    }


    private boolean validate(Position position) {
        boolean result = true;
        if (position.getX() > NUMBER_OF_BLOCKS || position.getX() < -NUMBER_OF_BLOCKS) {
            result = false;
        } else if (position.getY() > NUMBER_OF_BLOCKS || position.getY() < -NUMBER_OF_BLOCKS) {
            result = false;
        }
        return result;
    }
}
