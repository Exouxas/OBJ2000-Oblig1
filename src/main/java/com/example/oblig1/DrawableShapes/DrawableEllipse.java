package com.example.oblig1.DrawableShapes;

import com.example.oblig1.DrawStructure;
import com.example.oblig1.controls.CustomSetting;
import javafx.geometry.Point2D;
import javafx.scene.control.ColorPicker;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;

public class DrawableEllipse extends Ellipse implements IDrawable{
    private Point2D startPos;
    private Point2D endPos;

    private VBox settings;
    private DrawStructure structure;

    ColorPicker cp;


    public DrawableEllipse(DrawStructure structure){
        super();

        startPos = new Point2D(0, 0);
        endPos = new Point2D(0, 0);
        this.structure = structure;


        // Generate settings for this shape
        settings = new VBox();
        cp = new ColorPicker(Color.BLACK);
        CustomSetting colorSetting = new CustomSetting(
                "Color:",
                structure,
                cp);
        cp.setOnAction(e -> {
            setColor(cp.getValue());
        });
        settings.getChildren().add(colorSetting);
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

    @Override
    public IDrawable factory(DrawStructure structure) {
        return new DrawableEllipse(structure);
    }

    @Override
    public void setColor(Color color){
        this.setFill(color);
        this.setStroke(color);
        if(cp.getValue() != color){
            cp.setValue(color);
        }
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
