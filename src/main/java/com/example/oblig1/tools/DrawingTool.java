package com.example.oblig1.tools;

import com.example.oblig1.DrawStructure;
import com.example.oblig1.controls.CustomSetting;
import com.example.oblig1.controls.NumberSetting;
import com.example.oblig1.controls.ShapeSetting;
import javafx.scene.control.ColorPicker;

public class DrawingTool extends Tool{
    ShapeSetting shapeSetting = new ShapeSetting("Shape: ", drawStructure.getSettingHeight(), drawStructure.getSettingRatio());
    NumberSetting drawThickness = new NumberSetting("Thickness:", drawStructure.getSettingHeight(), drawStructure.getSettingRatio());
    NumberSetting textSize = new NumberSetting("Text size:", drawStructure.getSettingHeight(), drawStructure.getSettingRatio());
    ColorPicker picker = new ColorPicker();
    CustomSetting colorSetting = new CustomSetting("Color:", drawStructure.getSettingHeight(), drawStructure.getSettingRatio(), picker);


    public DrawingTool(DrawStructure drawStructure){
        super(drawStructure);

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

    @Override
    protected void cancel() {

    }
}
