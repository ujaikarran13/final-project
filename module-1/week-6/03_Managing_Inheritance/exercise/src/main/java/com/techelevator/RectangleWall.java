package com.techelevator;

public class RectangleWall extends Wall {
 private int height;
 private int length;

    public RectangleWall(String name, String color, int length, int height) {
        super(name, color);
        this.length = length;
        this.height = height;
    }

    public RectangleWall(String name, String color) {
        super(name, color);
    }

    public int getHeight() {
        return height;
    }

    public int getLength() {
        return length;
    }

    @Override
    public int getArea() {
        return length*height;
    }

    @Override
    public String toString() {
        return String.format("%s (%dx%d) rectangle", getName(), length, height);
    }
}
