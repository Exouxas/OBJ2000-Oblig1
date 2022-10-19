package com.example.oblig1;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.*;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.StringConverter;

import java.io.IOException;

public class DrawingProgram extends Application {
    Group drawnShapes = new Group();
    Pane drawArea = new Pane();
    Tool tool = Tool.Draw;
    Object selectedShape;


    // Properties for drawing
    double settingHeight = 25;
    double settingRatio = 40;
    NumberSetting drawThickness = new NumberSetting("Thickness:", settingHeight, settingRatio);
    NumberSetting textSize = new NumberSetting("Text size:", settingHeight, settingRatio);
    ColorPicker picker = new ColorPicker();

    // Current draw variables
    boolean mouseDown = false;
    double startX = 0;
    double startY = 0;
    double endX = 0;
    double endY = 0;


    @Override
    public void start(Stage stage) throws IOException {
        // Structure setup
        BorderPane mainSeparator = new BorderPane();

        drawArea.getChildren().add(drawnShapes);
        mainSeparator.setCenter(drawArea);
        drawArea.setOnMousePressed(e -> {
            mouseDown = true;
            startX = e.getX();
            startY = e.getY();
        });
        drawArea.setOnMouseReleased(e -> {mouseDown = false;});
        drawArea.setOnMouseMoved(e -> {mouseMoving(e.getX(), e.getY());});

        GridPane leftSide = new GridPane();
        mainSeparator.setLeft(leftSide);


        VBox properties = new VBox();
        leftSide.add(properties, 0, 0);

        properties.getChildren().add(drawThickness);
        properties.getChildren().add(textSize);

        CustomSetting colorSetting = new CustomSetting("Color:", settingHeight, settingRatio, picker);
        properties.getChildren().add(colorSetting);

        ShapeSetting shapeSetting = new ShapeSetting("Shape: ", settingHeight, settingRatio);
        shapeSetting.setConverter(new StringConverter<Object>(){
            @Override
            public String toString(Object obj){
                return ((Class)obj).getSimpleName();
            }

            @Override
            public Object fromString(String s){
                return null;
            }
        });
        shapeSetting.setOnAction(e -> {
            selectedShape = shapeSetting.getValue();
        });
        properties.getChildren().add(shapeSetting);

        ComboBox toolSelector = new ComboBox();
        toolSelector.getItems().add(Tool.Draw);
        toolSelector.getItems().add(Tool.Select);
        toolSelector.getSelectionModel().select(toolSelector.getItems().get(0));
        CustomSetting toolSetting = new CustomSetting("Tool:", settingHeight, settingRatio, toolSelector);
        properties.getChildren().add(toolSetting);


        leftSide.getColumnConstraints().add(new ColumnConstraints(200));




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

    private void mouseMoving(double x, double y){
        if(mouseDown){
            switch(tool){
                case Select:
                    break;
                case Draw:
                    if(selectedShape.equals(Rectangle.class)){
                        
                    }
                    break;
            }
        }
    }

    public static void main(String[] args) {
        launch();
    }

    enum Tool {
        Draw,
        Select
    }
}