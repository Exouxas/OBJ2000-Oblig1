package com.example.oblig1.tools;

import com.example.oblig1.DrawStructure;
import com.example.oblig1.DrawableShapes.IDrawable;
import com.example.oblig1.DrawableShapes.NamedDrawable;
import com.example.oblig1.controls.CustomSetting;
import com.example.oblig1.controls.NumberSetting;
import com.example.oblig1.controls.ShapeSetting;
import javafx.geometry.Point2D;
import javafx.scene.control.ColorPicker;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;

public class DrawingTool extends Tool{
    ShapeSetting shapeSetting = new ShapeSetting("Shape: ", drawStructure.getSettingHeight(), drawStructure.getSettingRatio());
    NumberSetting drawThickness = new NumberSetting("Thickness:", drawStructure.getSettingHeight(), drawStructure.getSettingRatio());
    NumberSetting textSize = new NumberSetting("Text size:", drawStructure.getSettingHeight(), drawStructure.getSettingRatio());
    ColorPicker picker = new ColorPicker();
    CustomSetting colorSetting = new CustomSetting("Color:", drawStructure.getSettingHeight(), drawStructure.getSettingRatio(), picker);

    IDrawable currentShape;
    CondensedMouseEvents selectEvents;


    public DrawingTool(DrawStructure drawStructure, CondensedMouseEvents selectEvents){
        super(drawStructure);

        this.selectEvents = selectEvents;

        picker.setValue(Color.BLACK);

        contextualProperties.getChildren().add(shapeSetting);
        contextualProperties.getChildren().add(colorSetting);
        contextualProperties.getChildren().add(drawThickness);
        contextualProperties.getChildren().add(textSize);

        // TODO: Add event for selection of shape to alter "contextualProperties"
    }

    @Override
    public void pressed(MouseEvent e) {
        double x = e.getX();
        double y = e.getY();

        selection.setSelected(null);
        Object selection = shapeSetting.getSelectionModel().getSelectedItem();
        currentShape = ((NamedDrawable)selection).getDrawable().factory();

        // Hurts a little to do it like this, but sometimes you just have to be forceful.
        drawStructure.getDrawArea().getChildren().add((Shape)currentShape);
        currentShape.setStart(new Point2D(x, y));
        currentShape.setEnd(new Point2D(x, y));
        ((Shape) currentShape).setFill(picker.getValue());

        // Set events to move shapes around and select them with the selection tool.
        ((Shape) currentShape).setOnMousePressed(selectEvents.pressed);
        ((Shape) currentShape).setOnMouseDragged(selectEvents.dragged);
        ((Shape) currentShape).setOnMouseReleased(selectEvents.released);
    }

    @Override
    public void moved(MouseEvent e) {
        if(currentShape != null){
            currentShape.setEnd(new Point2D(e.getX(), e.getY()));
        }
    }

    @Override
    public void released() {
        selection.setSelected(currentShape);
        currentShape = null;
    }

    @Override
    public String getName() {
        return "Drawing Tool";
    }

    @Override
    protected void cancel() {
        if(currentShape != null){
            drawStructure.getDrawArea().getChildren().remove((Shape)currentShape);
            currentShape = null;
        }
    }
}
