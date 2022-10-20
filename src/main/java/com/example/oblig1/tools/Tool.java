package com.example.oblig1.tools;

import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Shape;

public abstract class Tool {
    protected double settingHeight;
    protected double settingRatio;
    protected Pane interactiveArea;
    protected Pane propertiesRegion;
    protected VBox contextualProperties = new VBox();

    public Shape selected;

    public Tool(Pane interactiveArea, Pane properties, double settingHeight, double settingRatio){
        this.settingHeight = settingHeight;
        this.settingRatio = settingRatio;
        this.interactiveArea = interactiveArea;
        this.propertiesRegion = properties;
    }

    public abstract void pressed(double x, double y);
    public abstract void moved(double x, double y);
    public abstract void released();

    public abstract String getName();

    public void select(){
        if(!propertiesRegion.getChildren().contains(contextualProperties)){
            propertiesRegion.getChildren().add(contextualProperties);
        }
    }

    public void deselect(){
        if(propertiesRegion.getChildren().contains(contextualProperties)){
            propertiesRegion.getChildren().remove(contextualProperties);
        }

        cancel();
    }

    protected abstract void cancel();
}
