package com.techelevator;

public class SquareWall extends RectangleWall{

    public SquareWall(String name, String color, int sideLength) {
        super(name, color, sideLength, sideLength);

    }

    @Override
    public String toString() {
        return String.format("%s (%dx%d) square", getName(), getLength(), getHeight());
    }
}

