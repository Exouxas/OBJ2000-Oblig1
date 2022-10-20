package com.example.oblig1.tools;

import com.example.oblig1.DrawStructure;

public class SelectionTool extends Tool{
    public SelectionTool(DrawStructure drawStructure){
        super(drawStructure);
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
