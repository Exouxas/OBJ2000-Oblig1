package com.example.oblig1.tools;

import com.example.oblig1.DrawableShapes.IDrawable;
import javafx.animation.AnimationTimer;
import javafx.geometry.Point2D;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public class Selection extends Rectangle {
    private IDrawable selectedShape;
    public IDrawable getSelected(){
        return selectedShape;
    }
    public void setSelected(IDrawable newSelection){
        selectedShape = newSelection;
        if(selectedShape != null){
            resize(selectedShape);
            this.setOnMousePressed(((Shape)selectedShape).getOnMousePressed());
            this.setOnMouseDragged(((Shape)selectedShape).getOnMouseDragged());
            this.setOnMouseReleased(((Shape)selectedShape).getOnMouseReleased());
            this.toFront();
        }else{
            this.toBack();
        }
    }

    public Selection(Pane drawArea){
        setFill(Color.TRANSPARENT);

        // Add and move selection before it's possible to see
        drawArea.getChildren().add(this);
        this.toBack();

        // Make stroke visible, and animated
        setStroke(Color.BLACK);
        getStrokeDashArray().addAll(5d);
        AnimationTimer animatedBorder = new AnimationTimer() {
            @Override
            public void handle(long l) {
                setStrokeDashOffset(getStrokeDashOffset() + 0.1);
            }
        };
        animatedBorder.start();
    }

    private void resize(IDrawable s){
        Point2D pos = s.getRootPosition();
        Point2D size = s.getBounds();

        setX(pos.getX());
        setY(pos.getY());

        setWidth(size.getX());
        setHeight(size.getY());
    }
}
