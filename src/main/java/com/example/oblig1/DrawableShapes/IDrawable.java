package com.example.oblig1.DrawableShapes;

import com.example.oblig1.DrawStructure;
import javafx.geometry.Point2D;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

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
     * Basic factory method.
     * @param structure Collection of settings used for scaling the settings
     * @return Instance of object
     */
    IDrawable factory(DrawStructure structure);

    /**
     * Set color of the shape
     * @param color
     */
    void setColor(Color color);

    /**
     * Gets a VBox containing all settings for the selected shape
     * @return VBox containing settings
     */
    VBox getSettings();

    /**
     * Gets the width and height of the shape.
     * @return Width and height
     */
    Point2D getBounds();

    /**
     * Gets the "root position". (top left point of bounds)
     * @return Root position
     */
    Point2D getRootPosition();
}
