package com.example.oblig1.tools;

import javafx.scene.layout.Pane;
import javafx.scene.shape.Shape;

public abstract class Tool {
    protected double settingHeight;
    protected double settingRatio;
    protected Pane interactiveArea;
    protected Pane properties;

    public Shape selected;

    public Tool(Pane interactiveArea, Pane properties, double settingHeight, double settingRatio){
        this.settingHeight = settingHeight;
        this.settingRatio = settingRatio;
        this.interactiveArea = interactiveArea;
        this.properties = properties;
    }

    public abstract void pressed(double x, double y);
    public abstract void moved(double x, double y);
    public abstract void released();

    public abstract String getName();
}
