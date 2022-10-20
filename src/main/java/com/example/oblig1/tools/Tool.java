package com.example.oblig1.tools;

import com.example.oblig1.DrawStructure;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Shape;

public abstract class Tool {
    protected double settingHeight;
    protected double settingRatio;
    protected DrawStructure drawStructure;

    protected VBox contextualProperties = new VBox();
    protected VBox contextualInfo = new VBox();

    public Shape selected;

    public Tool(DrawStructure drawStructure, double settingHeight, double settingRatio){
        this.settingHeight = settingHeight;
        this.settingRatio = settingRatio;
        this.drawStructure = drawStructure;
    }

    public abstract void pressed(double x, double y);
    public abstract void moved(double x, double y);
    public abstract void released();

    public abstract String getName();

    public void select(){
        if(!drawStructure.getProperties().getChildren().contains(contextualProperties)){
            drawStructure.getProperties().getChildren().add(contextualProperties);
        }

        if(!drawStructure.getInfoBox().getChildren().contains(contextualInfo)){
            drawStructure.getInfoBox().getChildren().add(contextualInfo);
        }
    }

    public void deselect(){
        drawStructure.getProperties().getChildren().remove(contextualProperties);
        drawStructure.getInfoBox().getChildren().remove(contextualInfo);

        cancel();
    }

    protected abstract void cancel();
}
