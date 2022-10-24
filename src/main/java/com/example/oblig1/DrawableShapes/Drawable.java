package com.example.oblig1.DrawableShapes;

import javafx.geometry.Point2D;

public abstract class Drawable implements IMoveable, ISelectable {
    // IMoveable methods
    public abstract Point2D getPosition();
    public abstract void setPosition(Point2D position);
    public void translate(Point2D magnitude){
        setPosition(getPosition().add(magnitude));
    }
}
