package com.example.oblig1.DrawableShapes;

import javafx.geometry.Point2D;
import javafx.scene.shape.Rectangle;

public class DrawableRectangle extends Rectangle implements IDrawable{
    private Point2D startPos;
    private Point2D endPos;


    public DrawableRectangle(){
        startPos = new Point2D(0, 0);
        endPos = new Point2D(0, 0);
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

    private void recalculate(){
        Point2D minimum = new Point2D(
                Math.min(startPos.getX(), endPos.getX()),
                Math.min(startPos.getY(), endPos.getY())
        );

        Point2D maximum = new Point2D(
                Math.max(startPos.getX(), endPos.getX()),
                Math.max(startPos.getY(), endPos.getY())
        );

        Point2D size = maximum.subtract(minimum);


        setX(minimum.getX());
        setY(minimum.getY());

        setWidth(size.getX());
        setHeight(size.getY());
    }
}
