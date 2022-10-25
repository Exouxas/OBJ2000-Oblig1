package com.example.oblig1.tools;

import com.example.oblig1.DrawStructure;
import com.example.oblig1.DrawableShapes.IDrawable;
import javafx.geometry.Point2D;
import javafx.scene.input.MouseEvent;

public class SelectionTool extends Tool{
    private Point2D mousePositionWhenGrabbed;
    private Point2D shapePositionWhenGrabbed;
    private Point2D offset;


    public SelectionTool(DrawStructure drawStructure){
        super(drawStructure);

        drawStructure.getBackground().setOnMousePressed(e -> {selection.setSelected(null);});
    }

    @Override
    public void pressed(MouseEvent e) {
        if(e.getSource() != selection){
            selection.setSelected((IDrawable)e.getSource());
        }
        mousePositionWhenGrabbed = new Point2D(e.getX(), e.getY());
        if(e.getSource() != selection){
            shapePositionWhenGrabbed = ((IDrawable) e.getSource()).getPosition();
        }else{
            shapePositionWhenGrabbed = selection.getSelected().getPosition();
        }
        offset = shapePositionWhenGrabbed.subtract(mousePositionWhenGrabbed);
    }

    @Override
    public void moved(MouseEvent e) {
        Point2D newPosition = new Point2D(e.getX(), e.getY());
        selection.getSelected().setPosition(newPosition.add(offset));
    }

    @Override
    public void released() {
        mousePositionWhenGrabbed = null;
        shapePositionWhenGrabbed = null;
        offset = null;
    }

    @Override
    public String getName() {
        return "Selection Tool";
    }

    @Override
    protected void cancel() {

    }
}
