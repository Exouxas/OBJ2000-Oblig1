package com.example.oblig1.DrawableShapes;

import com.example.oblig1.DrawStructure;
import com.example.oblig1.ZOrderManager;
import com.example.oblig1.controls.CustomSetting;
import com.example.oblig1.controls.NumberSetting;
import javafx.geometry.Point2D;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class DrawableRectangle extends Rectangle implements IDrawable{
    private Point2D startPos;
    private Point2D endPos;

    private VBox settings;
    private DrawStructure structure;

    // Info labels
    Label typeLabel = new Label(getName());
    Label areaLabel = new Label();
    Label heightLabel = new Label();
    Label widthLabel = new Label();


    public DrawableRectangle(DrawStructure structure){
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
        settings.getChildren().add(heightLabel);
        settings.getChildren().add(widthLabel);
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

    @Override
    public IDrawable factory(DrawStructure structure) {
        return new DrawableRectangle(structure);
    }

    @Override
    public VBox getSettings() {
        return settings;
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

        areaLabel.setText("Area: " + getWidth() * getHeight() + " px^2");
        heightLabel.setText("Height: " + getHeight() + " px");
        widthLabel.setText("Width: " + getWidth() + " px");
    }
}
