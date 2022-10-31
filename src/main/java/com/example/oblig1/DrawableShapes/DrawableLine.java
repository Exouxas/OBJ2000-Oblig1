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
import javafx.scene.shape.Line;

public class DrawableLine extends Line implements IDrawable{
    private Point2D startPos;
    private Point2D endPos;

    private VBox settings;
    private DrawStructure structure;

    // Info labels
    Label typeLabel = new Label(getName());
    Label lengthLabel = new Label();


    public DrawableLine(DrawStructure structure){
        super();

        startPos = new Point2D(0, 0);
        endPos = new Point2D(0, 0);
        this.structure = structure;


        // Generate settings for this shape
        settings = new VBox();

        // Changeable
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
        settings.getChildren().add(lengthLabel);
    }

    @Override
    public String getName(){
        return "Line";
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
        return new DrawableLine(structure);
    }

    @Override
    public VBox getSettings() {
        return settings;
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
        Point2D minimum = new Point2D(
                Math.min(startPos.getX(), endPos.getX()),
                Math.min(startPos.getY(), endPos.getY()));

        return minimum;
    }

    private void recalculate(){
        setStartX(startPos.getX());
        setStartY(startPos.getY());
        setEndX(endPos.getX());
        setEndY(endPos.getY());

        lengthLabel.setText("Length: " + (int)(startPos.distance(endPos) * 100) / 100d + " px");
    }
}
