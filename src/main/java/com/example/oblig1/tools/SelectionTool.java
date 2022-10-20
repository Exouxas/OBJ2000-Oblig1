package com.example.oblig1.tools;

import javafx.scene.layout.Pane;

public class SelectionTool extends Tool{
    public SelectionTool(Pane interactiveArea, Pane properties, Pane infoBox, double settingHeight, double settingRatio){
        super(interactiveArea, properties, infoBox, settingHeight, settingRatio);
    }

    @Override
    public void pressed(double x, double y) {

    }

    @Override
    public void moved(double x, double y) {

    }

    @Override
    public void released() {

    }

    @Override
    public String getName() {
        return "Selection Tool";
    }

    @Override
    protected void cancel() {

    }
}
