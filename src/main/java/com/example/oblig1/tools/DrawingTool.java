package com.example.oblig1.tools;

import com.example.oblig1.controls.CustomSetting;
import com.example.oblig1.controls.NumberSetting;
import com.example.oblig1.controls.ShapeSetting;
import javafx.scene.control.ColorPicker;
import javafx.scene.layout.Pane;

public class DrawingTool extends Tool{
    ShapeSetting shapeSetting = new ShapeSetting("Shape: ", settingHeight, settingRatio);
    NumberSetting drawThickness = new NumberSetting("Thickness:", settingHeight, settingRatio);
    NumberSetting textSize = new NumberSetting("Text size:", settingHeight, settingRatio);
    ColorPicker picker = new ColorPicker();
    CustomSetting colorSetting = new CustomSetting("Color:", settingHeight, settingRatio, picker);


    public DrawingTool(Pane interactiveArea, Pane properties, double settingHeight, double settingRatio){
        super(interactiveArea, properties, settingHeight, settingRatio);

        contextualProperties.getChildren().add(shapeSetting);
        contextualProperties.getChildren().add(colorSetting);
        contextualProperties.getChildren().add(drawThickness);
        contextualProperties.getChildren().add(textSize);
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
        return "Drawing Tool";
    }
}
