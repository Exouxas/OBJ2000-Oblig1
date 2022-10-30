package com.example.oblig1;

import com.example.oblig1.controls.*;
import com.example.oblig1.tools.CondensedMouseEvents;
import com.example.oblig1.tools.DrawingTool;
import com.example.oblig1.tools.SelectionTool;
import com.example.oblig1.tools.Tool;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.StringConverter;

import java.util.ArrayList;

public class DrawingProgram extends Application {
    // Structures
    DrawStructure drawStructure = new DrawStructure(25, 200, 40);

    // Tools
    ArrayList<Tool> tools;
    Tool tool;

    CondensedMouseEvents selectEvents;

    @Override
    public void start(Stage stage){
        // Structure setup
        drawStructure.getMainSeparator().add(drawStructure.getProperties(), 0, 0);
        ComboSetting toolSetting = new ComboSetting("Tool:", drawStructure);
        drawStructure.getProperties().getChildren().add(toolSetting);
        toolSetting.getSelectionModel().selectedItemProperty().addListener((options, oldValue, newValue) -> {
            tool = (Tool)newValue;
            if(oldValue != null) {((Tool)oldValue).deselect();}
            ((Tool)newValue).select();
        });

        SelectionTool selectionTool = new SelectionTool(drawStructure);
        selectEvents = new CondensedMouseEvents();
        selectEvents.pressed = e -> {
            selectionTool.pressed(e);
        };
        selectEvents.dragged = e -> {
            selectionTool.moved(e);
        };
        selectEvents.released = e -> {
            selectionTool.released();
        };

        DrawingTool drawingTool = new DrawingTool(drawStructure, selectEvents);
        tool = drawingTool;

        toolSetting.addValue(drawingTool);
        toolSetting.addValue(selectionTool);
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
        drawStructure.getDrawArea().setOnMousePressed(e -> {
            if(tool == drawingTool){
                tool.pressed(e);
            }
        });
        drawStructure.getDrawArea().setOnMouseDragged(e -> {
            if(tool == drawingTool) {
                tool.moved(e);
            }
        });
        drawStructure.getDrawArea().setOnMouseReleased(e -> {
            if(tool == drawingTool) {
                tool.released();
            }
        });


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