package com.example.oblig1.tools;

import javafx.scene.shape.Shape;

public abstract class Tool {
    public Shape selected;

    public Tool(){

    }

    public abstract void pressed(double x, double y);
    public abstract void moved(double x, double y);
    public abstract void released();

    public abstract String getName();
}
