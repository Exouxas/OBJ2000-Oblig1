package com.example.oblig1.DrawableShapes;

import javafx.geometry.Point2D;
import javafx.scene.shape.Circle;

public class DrawableCircle extends Circle implements IDrawable{
    private Point2D startPos;
    private Point2D endPos;


    public DrawableCircle(){
        super();

        startPos = new Point2D(0, 0);
        endPos = new Point2D(0, 0);
    }

    @Override
    public String getName(){
        return "Circle";
    }

    @Override
    public Point2D getStart() {
        return startPos;
    }

    @Override
    public void setStart(Point2D point) {
        startPos = point;
        recalculate();
    }

    @Override
    public Point2D getEnd() {
        return endPos;
    }

    @Override
    public void setEnd(Point2D point) {
        endPos = point;
        recalculate();
    }

    public IDrawable factory(){
        return new DrawableCircle();
    }

    private void recalculate(){
        // Set position
        setCenterX(startPos.getX());
        setCenterY(startPos.getY());

        // Set radius
        setRadius(startPos.distance(endPos));
    }
}
