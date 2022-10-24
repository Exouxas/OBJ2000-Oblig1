package com.example.oblig1.DrawableShapes;

import javafx.geometry.Point2D;

public interface IDrawable {
    /**
     * Gets the name of the shape.
     * @return Name
     */
    String getName();

    /**
     * Gets the starting position of a drawn shape.
     * @return Starting position
     */
    Point2D getStart();

    /**
     * Sets the starting position of a drawn shape.
     * @param point Starting position
     */
    void setStart(Point2D point);


    /**
     * Gets the ending position of a drawn shape.
     * @return Ending position
     */
    Point2D getEnd();

    /**
     * Sets the ending position of a drawn shape.
     * @param point Ending position
     */
    void setEnd(Point2D point);


    /**
     * Gets the position of the shape. By default, gets the start position.
     * @return Position
     */
    default Point2D getPosition(){
        return getStart();
    }

    /**
     * Sets the position of the shape. By default, translates both the start and end point equally.
     * @param position Position
     */
    default void setPosition(Point2D position){
        setEnd(getEnd().subtract(getStart()).add(position)); // Translate end equally
        setStart(position);
    }

    /**
     * Move the shape a direction.
     * @param magnitude Direction and magnitude
     */
    default void translate(Point2D magnitude){
        setPosition(getPosition().add(magnitude));
    }

    /**
     * Basic factory method.
     * @return Instance of object
     */
    IDrawable factory();
}
