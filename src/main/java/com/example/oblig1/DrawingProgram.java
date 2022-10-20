package com.example.oblig1;

import com.example.oblig1.controls.*;
import com.example.oblig1.tools.DrawingTool;
import com.example.oblig1.tools.SelectionTool;
import com.example.oblig1.tools.Tool;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.*;
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
        GridPane mainSeparator = new GridPane();
        mainSeparator.getColumnConstraints().add(new ColumnConstraints(200));
        ColumnConstraints cc = new ColumnConstraints();
        cc.setPercentWidth(100);
        mainSeparator.getColumnConstraints().add(cc);
        mainSeparator.getColumnConstraints().add(new ColumnConstraints(200));
        RowConstraints rc = new RowConstraints();
        rc.setPercentHeight(100);
        mainSeparator.getRowConstraints().add(rc);



        mainSeparator.add(drawStructure.getProperties(), 0, 0);
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



        mainSeparator.add(drawStructure.getDrawArea(), 1, 0);
        drawStructure.getDrawArea().setOnMousePressed(e -> tool.pressed(e.getX(), e.getY()));
        drawStructure.getDrawArea().setOnMouseMoved(e -> tool.moved(e.getX(), e.getY()));
        drawStructure.getDrawArea().setOnMouseReleased(e -> tool.released());



        mainSeparator.add(drawStructure.getInfoBox(), 2, 0);



        // Window initiation
        Scene scene = new Scene(mainSeparator, 1280, 720);
        stage.setTitle("Drawing program");
        stage.setScene(scene);
        stage.show();




        /*
        Circle c1 = new Circle();
        Circle c2 = new Circle();

        c1.setCenterX(scene.getWidth()/3);
        c2.setCenterX((scene.getWidth()/3)+50);

        c1.setCenterY(scene.getHeight()/2);
        c2.setCenterY(scene.getHeight()/2);

        c1.setRadius(50);
        c2.setRadius(50);


        c2.setFill(Color.TRANSPARENT);
        c2.setStroke(Color.RED);
        c2.setStrokeWidth(10);

        drawnShapes.getChildren().add(c1);
        drawnShapes.getChildren().add(c2);

        c1.setOnMouseClicked(e -> {c1.setFill(Color.BLUE);});
        c2.setOnMouseClicked(e -> {c2.setFill(Color.BLUE);});
        */
    }


    public static void main(String[] args) {
        launch();
    }
}