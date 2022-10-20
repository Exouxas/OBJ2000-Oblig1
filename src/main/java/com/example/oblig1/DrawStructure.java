package com.example.oblig1;

import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

import java.util.Random;

public class DrawStructure {
    Random r = new Random();



    private Pane drawArea = new Pane();
    public Pane getDrawArea() {
        return drawArea;
    }

    private Rectangle background = new Rectangle();
    public Rectangle getBackground() {
        return background;
    }

    private VBox properties = new VBox();
    public VBox getProperties() {
        return properties;
    }

    private VBox infoBox = new VBox();
    public VBox getInfoBox() {
        return infoBox;
    }

    public DrawStructure(){
        background.setFill(Color.WHITE);
        drawArea.getChildren().add(background);
        background.setWidth(drawArea.getWidth());
        background.setHeight(drawArea.getHeight());
        drawArea.widthProperty().addListener((e, oldValue, newValue) -> {
            background.setWidth((double)newValue);
        });
        drawArea.heightProperty().addListener((e, oldValue, newValue) -> {
            background.setHeight((double)newValue);
        });

        background.setOnMousePressed(e1 -> {
            Circle c = new Circle();
            drawArea.getChildren().add(c);
            c.setCenterX(drawArea.getWidth() * r.nextDouble());
            c.setCenterY(drawArea.getHeight() * r.nextDouble());
            c.setRadius(10);
            c.setOnMouseClicked(e2 -> {c.setFill(Color.BLUE);});
        });
    }
}
