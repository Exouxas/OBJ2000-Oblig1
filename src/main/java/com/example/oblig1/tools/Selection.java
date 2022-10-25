package com.example.oblig1.tools;

import com.example.oblig1.DrawableShapes.ISelectable;
import javafx.animation.AnimationTimer;
import javafx.geometry.Point2D;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Selection extends Rectangle {
    private ISelectable selectedShape;
    public ISelectable getSelected(){
        return selectedShape;
    }
    public void setSelected(ISelectable newSelection){
        selectedShape = newSelection;
        if(selectedShape != null){
            resize(selectedShape);
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

    private void resize(ISelectable s){
        Point2D pos = s.getRootPosition();
        Point2D size = s.getBounds();

        setX(pos.getX());
        setY(pos.getY());

        setWidth(size.getX());
        setHeight(size.getY());
    }
}
