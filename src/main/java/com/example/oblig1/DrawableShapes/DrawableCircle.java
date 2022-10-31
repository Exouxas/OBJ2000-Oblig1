package com.example.oblig1.DrawableShapes;

import com.example.oblig1.DrawStructure;
import com.example.oblig1.controls.CustomSetting;
import com.example.oblig1.controls.NumberSetting;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.util.LinkedList;

public class DrawableCircle extends Circle implements IDrawable{
    private Point2D startPos;
    private Point2D endPos;

    private VBox settings;
    private DrawStructure structure;

    // Info labels
    Label typeLabel = new Label(getName());
    Label areaLabel = new Label();
    Label radiusLabel = new Label();


    public DrawableCircle(DrawStructure structure){
        super();

        startPos = new Point2D(0, 0);
        endPos = new Point2D(0, 0);
        this.structure = structure;


        // Generate settings for this shape
        settings = new VBox();

        // Changeable
        ColorPicker fillPicker = new ColorPicker(Color.BLACK);
        CustomSetting fillSetting = new CustomSetting(
                "Fill color:",
                structure,
                fillPicker);
        fillPicker.setOnAction(e -> {
            setFill(fillPicker.getValue());
        });
        settings.getChildren().add(fillSetting);

        ColorPicker strokePicker = new ColorPicker(Color.BLACK);
        CustomSetting strokeSetting = new CustomSetting(
                "Stroke color:",
                structure,
                strokePicker);
        strokePicker.setOnAction(e -> {
            setStroke(strokePicker.getValue());
        });
        settings.getChildren().add(strokeSetting);

        NumberSetting strokeThicknessSetting = new NumberSetting(
                "Stroke thickness:",
                structure
        );
        strokeThicknessSetting.setOnAction(e -> {
            setStrokeWidth(((NumberSetting)e.getSource()).getValue());
        });
        strokeThicknessSetting.setValue(getStrokeWidth());
        settings.getChildren().add(strokeThicknessSetting);

        Button front = new Button("To front");
        CustomSetting frontSetting = new CustomSetting("To front:", structure, front);
        settings.getChildren().add(frontSetting);
        front.setOnAction(e -> {
            toFront();
        });

        Button fwd = new Button("Forward");
        CustomSetting fwdSetting = new CustomSetting("Forward:", structure, fwd);
        settings.getChildren().add(fwdSetting);
        fwd.setOnAction(e -> {
            zOrder(1);
        });

        Button bwd = new Button("Backward");
        CustomSetting bwdSetting = new CustomSetting("Backward:", structure, bwd);
        settings.getChildren().add(bwdSetting);
        bwd.setOnAction(e -> {
            zOrder(-1);
        });

        Button back = new Button("To back");
        CustomSetting backSetting = new CustomSetting("To back:", structure, back);
        settings.getChildren().add(backSetting);
        back.setOnAction(e -> {
            toBack();
        });



        // Info
        settings.getChildren().add(typeLabel);
        settings.getChildren().add(areaLabel);
        settings.getChildren().add(radiusLabel);

    }

    private void zOrder(int positionToMove){ // Doesn't function as expected, but should be close to functioning
        LinkedList<Node> l1 = new LinkedList<>();
        LinkedList<Node> l2 = new LinkedList<>();

        boolean found = false;
        for(Node n : structure.getDrawOrder()){
            if(found){
                l2.add(n);
            }else{
                if(n == this){
                    found = true;
                }else{
                    l1.add(n);
                }
            }
        }

        for(int i = 0; i < Math.abs(positionToMove); i++){
            if(positionToMove > 0){
                if(l2.size() > 0){
                    Node n = l2.getFirst();
                    l1.add(n);
                    l2.remove(n);
                }
            } else if (positionToMove < 0) {
                if(l1.size() > 0) {
                    Node n = l1.getLast();
                    l2.addFirst(n);
                    l1.remove(n);
                }
            }
        }

        for(int i = l1.size() - 1; i >= 0; i--){
            l1.get(i).toBack();
        }

        for(int i = 0; i < l2.size(); i++){
            l2.get(i).toFront();
        }

        structure.getBackground().toBack();

        structure.getDrawOrder().removeAll(l1);
        structure.getDrawOrder().remove(this);
        structure.getDrawOrder().removeAll(l2);

        structure.getDrawOrder().addAll(l1);
        structure.getDrawOrder().add(this);
        structure.getDrawOrder().addAll(l2);

    }

    @Override
    public String getName(){
        return "Circle";
    }

    @Override
    public Point2D getStart() {
        return startPos;
    }

    @Override
    public void setStart(Point2D point) {
        startPos = point;
        recalculate();
    }

    @Override
    public Point2D getEnd() {
        return endPos;
    }

    @Override
    public void setEnd(Point2D point) {
        endPos = point;
        recalculate();
    }

    @Override
    public IDrawable factory(DrawStructure structure){
        return new DrawableCircle(structure);
    }

    @Override
    public VBox getSettings(){
        return settings;
    }

    @Override
    public Point2D getBounds() {
        return new Point2D(getRadius(), getRadius()).multiply(2);
    }

    @Override
    public Point2D getRootPosition() {
        return startPos.subtract(new Point2D(getRadius(), getRadius()));
    }

    private void recalculate(){
        // Set position
        setCenterX(startPos.getX());
        setCenterY(startPos.getY());

        // Set radius
        setRadius(startPos.distance(endPos));

        areaLabel.setText("Area: " + (int)(Math.pow(getRadius(), 2) * Math.PI * 100) / 100d + " px^2");
        radiusLabel.setText("Radius: " + (int)(getRadius() * 100) / 100d + " px");
    }
}
