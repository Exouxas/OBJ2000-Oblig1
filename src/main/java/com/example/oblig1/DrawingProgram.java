package com.example.oblig1;

import com.example.oblig1.controls.*;
import com.example.oblig1.tools.DrawingTool;
import com.example.oblig1.tools.SelectionTool;
import com.example.oblig1.tools.Tool;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.StringConverter;

import java.util.ArrayList;
import java.util.List;

public class DrawingProgram extends Application {
    // Structures
    DrawStructure drawStructure = new DrawStructure(25, 200, 40);

    // Tools
    ArrayList<Tool> tools = new ArrayList<>(List.of(
            new DrawingTool(drawStructure),
            new SelectionTool(drawStructure)
    ));
    Tool tool = tools.get(0);


    @Override
    public void start(Stage stage){
        // Structure setup
        drawStructure.getMainSeparator().add(drawStructure.getProperties(), 0, 0);
        ComboSetting toolSetting = new ComboSetting("Tool:", drawStructure.getSettingHeight(), drawStructure.getSettingRatio());
        drawStructure.getProperties().getChildren().add(toolSetting);
        toolSetting.getSelectionModel().selectedItemProperty().addListener((options, oldValue, newValue) -> {
            tool = (Tool)newValue;
            if(oldValue != null) {((Tool)oldValue).deselect();}
            ((Tool)newValue).select();
        });
        toolSetting.addValue(tools.get(0));
        toolSetting.addValue(tools.get(1));
        toolSetting.setConverter(new StringConverter<>(){
            @Override
            public String toString(Object obj){
                return ((Tool)obj).getName();
            }

            @Override
            public Object fromString(String s){
                return null;
            }
        });



        drawStructure.getMainSeparator().add(drawStructure.getDrawArea(), 1, 0);
        drawStructure.getDrawArea().setOnMousePressed(e -> tool.pressed(e.getX(), e.getY()));
        drawStructure.getDrawArea().setOnMouseDragged(e -> tool.moved(e.getX(), e.getY()));
        drawStructure.getDrawArea().setOnMouseReleased(e -> tool.released());


        drawStructure.getMainSeparator().add(drawStructure.getInfoBox(), 2, 0);



        // Window initiation
        Scene scene = new Scene(drawStructure.getMainSeparator(), 1280, 720);
        stage.setTitle("Drawing program");
        stage.setScene(scene);
        stage.show();
    }


    public static void main(String[] args) {
        launch();
    }
}