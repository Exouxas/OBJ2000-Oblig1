package com.example.oblig1.DrawableShapes;

import com.example.oblig1.DrawStructure;
import com.example.oblig1.controls.CustomSetting;
import com.example.oblig1.controls.NumberSetting;
import javafx.geometry.Point2D;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class DrawableCircle extends Circle implements IDrawable{
    private Point2D startPos;
    private Point2D endPos;

    private VBox settings;
    private DrawStructure structure;


    public DrawableCircle(DrawStructure structure){
        super();

        startPos = new Point2D(0, 0);
        endPos = new Point2D(0, 0);
        this.structure = structure;


        // Generate settings for this shape
        settings = new VBox();

        // Changeable
        ColorPicker fillPicker = new ColorPicker(Color.BLACK);
        CustomSetting fillSetting = new CustomSetting(
                "Fill color:",
                structure,
                fillPicker);
        fillPicker.setOnAction(e -> {
            setFill(fillPicker.getValue());
        });
        settings.getChildren().add(fillSetting);

        ColorPicker strokePicker = new ColorPicker(Color.BLACK);
        CustomSetting strokeSetting = new CustomSetting(
                "Stroke color:",
                structure,
                strokePicker);
        strokePicker.setOnAction(e -> {
            setStroke(strokePicker.getValue());
        });
        settings.getChildren().add(strokeSetting);

        NumberSetting strokeThicknessSetting = new NumberSetting(
                "Stroke thickness:",
                structure
        );
        strokeThicknessSetting.setOnAction(e -> {
            setStrokeWidth(((NumberSetting)e.getSource()).getValue());
        });
        strokeThicknessSetting.setValue(getStrokeWidth());
        settings.getChildren().add(strokeThicknessSetting);



        // Info
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

    @Override
    public IDrawable factory(DrawStructure structure){
        return new DrawableCircle(structure);
    }

    @Override
    public VBox getSettings(){
        return settings;
    }

    @Override
    public Point2D getBounds() {
        return new Point2D(getRadius(), getRadius()).multiply(2);
    }

    @Override
    public Point2D getRootPosition() {
        return startPos.subtract(new Point2D(getRadius(), getRadius()));
    }

    private void recalculate(){
        // Set position
        setCenterX(startPos.getX());
        setCenterY(startPos.getY());

        // Set radius
        setRadius(startPos.distance(endPos));
    }
}
