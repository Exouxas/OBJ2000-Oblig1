package com.example.oblig1.DrawableShapes;

import com.example.oblig1.DrawStructure;
import com.example.oblig1.ZOrderManager;
import com.example.oblig1.controls.CustomSetting;
import com.example.oblig1.controls.NumberSetting;
import javafx.collections.ObservableList;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.util.LinkedList;

public class DrawableCircle extends Circle implements IDrawable{
    private Point2D startPos;
    private Point2D endPos;

    private VBox settings;
    private DrawStructure structure;

    // Info labels
    Label typeLabel = new Label(getName());
    Label areaLabel = new Label();
    Label radiusLabel = new Label();


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

        new ZOrderManager(this, settings, structure);



        // Info
        settings.getChildren().add(typeLabel);
        settings.getChildren().add(areaLabel);
        settings.getChildren().add(radiusLabel);

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

        areaLabel.setText("Area: " + (int)(Math.pow(getRadius(), 2) * Math.PI * 100) / 100d + " px^2");
        radiusLabel.setText("Radius: " + (int)(getRadius() * 100) / 100d + " px");
    }
}
