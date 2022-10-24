package com.example.oblig1.DrawableShapes;

import javafx.geometry.Point2D;

public interface IMoveable {
    Point2D getPosition();

    void setPosition(Point2D position);

    default void translate(Point2D magnitude){
        setPosition(getPosition().add(magnitude));
    }
}
