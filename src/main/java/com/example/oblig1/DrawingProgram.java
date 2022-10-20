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
    // Settings
    double settingHeight = 25;
    double settingRatio = 40;

    // Structures
    Pane drawArea = new Pane();
    VBox properties = new VBox();

    // Tools
    ArrayList<Tool> tools = new ArrayList<>(List.of(
            new DrawingTool(drawArea, properties, settingHeight, settingRatio),
            new SelectionTool(drawArea, properties, settingHeight, settingRatio)
    ));
    Tool tool = tools.get(0);


    @Override
    public void start(Stage stage){
        // Structure setup
        BorderPane mainSeparator = new BorderPane();

        mainSeparator.setCenter(drawArea);
        drawArea.setOnMousePressed(e -> tool.pressed(e.getX(), e.getY()));
        drawArea.setOnMouseMoved(e -> tool.moved(e.getX(), e.getY()));
        drawArea.setOnMouseReleased(e -> tool.released());

        GridPane leftSide = new GridPane();
        mainSeparator.setLeft(leftSide);
        leftSide.getColumnConstraints().add(new ColumnConstraints(200));
        leftSide.add(properties, 0, 0);


        ComboSetting toolSetting = new ComboSetting("Tool:", settingHeight, settingRatio);
        properties.getChildren().add(toolSetting);
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