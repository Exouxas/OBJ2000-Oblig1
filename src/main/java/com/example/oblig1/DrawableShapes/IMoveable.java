package com.example.oblig1.DrawableShapes;

import javafx.geometry.Point2D;

public interface IMoveable {
    public Point2D getPosition();

    public void setPosition(Point2D position);

    public default void translate(Point2D magnitude){
        setPosition(getPosition().add(magnitude));
    };
}
