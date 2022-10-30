package com.example.oblig1.tools;

import com.example.oblig1.DrawStructure;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

public abstract class Tool {
    protected DrawStructure drawStructure;

    protected VBox contextualProperties = new VBox();
    protected VBox contextualInfo = new VBox();

    public static Selection selection;
    protected boolean isSelected;

    public Tool(DrawStructure drawStructure){
        this.drawStructure = drawStructure;
        selection = new Selection(drawStructure);
    }

    public abstract void pressed(MouseEvent e);
    public abstract void moved(MouseEvent e);
    public abstract void released();

    public abstract String getName();

    public void select(){
        if(!drawStructure.getProperties().getChildren().contains(contextualProperties)){
            drawStructure.getProperties().getChildren().add(contextualProperties);
        }

        if(!drawStructure.getInfoBox().getChildren().contains(contextualInfo)){
            drawStructure.getInfoBox().getChildren().add(contextualInfo);
        }

        isSelected = true;
    }

    public void deselect(){
        drawStructure.getProperties().getChildren().remove(contextualProperties);
        drawStructure.getInfoBox().getChildren().remove(contextualInfo);

        isSelected = false;
        cancel();
    }

    protected abstract void cancel();
}
