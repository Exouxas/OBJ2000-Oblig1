package com.example.oblig1.DrawableShapes;

import javafx.geometry.Point2D;

public interface IDrawable {
    /**
     * Gets the starting position of a drawn shape.
     * @return Starting position
     */
    public Point2D getStart();

    /**
     * Sets the starting position of a drawn shape.
     * @param point Starting position
     */
    public void setStart(Point2D point);


    /**
     * Gets the ending position of a drawn shape.
     * @return Ending position
     */
    public Point2D getEnd();

    /**
     * Sets the ending position of a drawn shape.
     * @param point Ending position
     */
    public void setEnd(Point2D point);
}
