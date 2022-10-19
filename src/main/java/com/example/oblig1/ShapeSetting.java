package com.example.oblig1;

import javafx.event.EventHandler;
import javafx.scene.control.ComboBox;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.util.StringConverter;

public class ShapeSetting extends ComboSetting {
    public ShapeSetting(String text, double height, double labelPercent){
        super(text, height, labelPercent);


        box.getItems().add(Rectangle.class);
        box.getItems().add(Circle.class);
        box.getItems().add(Ellipse.class);
        box.getItems().add(Line.class);
        box.getItems().add(Text.class);
        this.setConverter(new StringConverter<Object>(){
            @Override
            public String toString(Object obj){
                return ((Class)obj).getSimpleName();
            }

            @Override
            public Object fromString(String s){
                return null;
            }
        });

        setValue(0);
    }
}
