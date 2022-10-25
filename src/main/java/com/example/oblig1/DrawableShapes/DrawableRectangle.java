package com.example.oblig1.DrawableShapes;

import javafx.geometry.Point2D;
import javafx.scene.shape.Rectangle;

public class DrawableRectangle extends Rectangle implements IDrawable{
    private Point2D startPos;
    private Point2D endPos;


    public DrawableRectangle(){
        super();

        startPos = new Point2D(0, 0);
        endPos = new Point2D(0, 0);
    }

    @Override
    public String getName(){
        return "Rectangle";
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
        return new DrawableRectangle();
    }

    @Override
    public Point2D getBounds() {
        return new Point2D(
                Math.max(startPos.getX(), endPos.getX()),
                Math.max(startPos.getY(), endPos.getY())
        ).subtract(getRootPosition());
    }

    @Override
    public Point2D getRootPosition() {
        return new Point2D(
                Math.min(startPos.getX(), endPos.getX()),
                Math.min(startPos.getY(), endPos.getY())
        );
    }

    private void recalculate(){
        Point2D root = getRootPosition();

        Point2D size = getBounds();

        setX(root.getX());
        setY(root.getY());

        setWidth(size.getX());
        setHeight(size.getY());
    }
}
