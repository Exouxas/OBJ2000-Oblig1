package com.example.oblig1.DrawableShapes;

import javafx.geometry.Point2D;
import javafx.scene.shape.Rectangle;

public class DrawableRectangle implements IMoveable{
    private Rectangle shape;

    public DrawableRectangle(){
        shape = new Rectangle();
    }

    @Override
    public Point2D getPosition() {
        return null;
    }

    @Override
    public void setPosition(Point2D position) {

    }
}
