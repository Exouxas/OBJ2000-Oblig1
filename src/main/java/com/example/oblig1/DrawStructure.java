package com.example.oblig1;

import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

import java.util.Random;

public class DrawStructure {
    Random r = new Random();



    private GridPane mainSeparator = new GridPane();
    public GridPane getMainSeparator() {
        return mainSeparator;
    }

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

    private double settingHeight;
    public double getSettingHeight() {
        return settingHeight;
    }

    private double settingWidth;
    public double getSettingWidth() {
        return settingWidth;
    }

    private double settingRatio;
    public double getSettingRatio() {
        return settingRatio;
    }

    public DrawStructure(double settingHeight, double settingWidth, double settingRatio){
        this.settingHeight = settingHeight;
        this.settingWidth = settingWidth;
        this.settingRatio = settingRatio;

        // Left column
        mainSeparator.getColumnConstraints().add(new ColumnConstraints(settingWidth));

        // Middle column
        ColumnConstraints cc = new ColumnConstraints();
        cc.setHgrow(Priority.ALWAYS);
        mainSeparator.getColumnConstraints().add(cc);

        // Right column
        mainSeparator.getColumnConstraints().add(new ColumnConstraints(settingWidth));

        // height
        RowConstraints rc = new RowConstraints();
        rc.setPercentHeight(100);
        mainSeparator.getRowConstraints().add(rc);


        // Clipping
        Rectangle clipRect = new Rectangle();
        drawArea.setClip(clipRect);

        background.setFill(Color.WHITE);
        drawArea.getChildren().add(background);
        drawArea.widthProperty().addListener((e, oldValue, newValue) -> {
            background.setWidth((double)newValue);
            clipRect.setWidth((double)newValue);
        });
        drawArea.heightProperty().addListener((e, oldValue, newValue) -> {
            background.setHeight((double)newValue);
            clipRect.setHeight((double)newValue);
        });

        background.setOnMousePressed(e1 -> {
            Circle c = new Circle();
            drawArea.getChildren().add(c);
            c.setRadius(100);
            c.setCenterX(drawArea.getWidth() * r.nextDouble());
            c.setCenterY(drawArea.getHeight() * r.nextDouble());
            c.setOnMouseClicked(e2 -> {c.setFill(Color.BLUE);});
        });
    }
}
