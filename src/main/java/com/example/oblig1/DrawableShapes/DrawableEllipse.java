package com.example.oblig1.DrawableShapes;

import javafx.geometry.Point2D;
import javafx.scene.shape.Ellipse;

public class DrawableEllipse extends Ellipse implements IDrawable{
    private Point2D startPos;
    private Point2D endPos;


    public DrawableEllipse(){
        super();

        startPos = new Point2D(0, 0);
        endPos = new Point2D(0, 0);
    }

    @Override
    public String getName(){
        return "Ellipse";
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
        return new DrawableEllipse();
    }

    @Override
    public Point2D getBounds() {
        Point2D maximum = new Point2D(
                Math.max(startPos.getX(), endPos.getX()),
                Math.max(startPos.getY(), endPos.getY()));

        return maximum.subtract(getRootPosition());
    }

    @Override
    public Point2D getRootPosition() {
        return new Point2D(
                Math.min(startPos.getX(), endPos.getX()),
                Math.min(startPos.getY(), endPos.getY()));
    }

    private void recalculate(){
        Point2D center = startPos.add(endPos).multiply(0.5);
        Point2D scale = getBounds().multiply(0.5);

        setCenterX(center.getX());
        setCenterY(center.getY());

        setRadiusX(scale.getX());
        setRadiusY(scale.getY());
    }
}
