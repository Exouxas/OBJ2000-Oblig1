package com.example.oblig1.DrawableShapes;

import javafx.geometry.Point2D;

public interface IMoveable {
    public void translate(Point2D magnitude);
    public Point2D getPosition();
    public void setPosition(Point2D position);
}
