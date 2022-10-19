package com.example.oblig1;

import javafx.event.EventHandler;
import javafx.scene.control.ComboBox;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.util.StringConverter;

public class ComboSetting extends CustomSetting {
    protected ComboBox<Object> box;


    public Object getValue(){
        return box.getValue();
    }
    public void setValue(int value){
        box.getSelectionModel().select(box.getItems().get(value));
    }


    public ComboSetting(String text, double height, double labelPercent){
        super(text, height, labelPercent, new ComboBox<Object>());

        box = (ComboBox)control;

        box.getItems().add(Rectangle.class);
        box.getItems().add(Circle.class);
        box.getItems().add(Ellipse.class);
        box.getItems().add(Line.class);
        box.getItems().add(Text.class);



        setValue(0); // Maybe add this into an event instead, and run every time length is changed. Check if selected one is still there.
    }

    public void setOnAction(EventHandler e){
        box.setOnAction(e);
    }

    public void setConverter(StringConverter<Object> converter){
        box.setConverter(converter);
    }
}
