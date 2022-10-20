package com.example.oblig1.controls;

import javafx.scene.shape.Circle;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.util.StringConverter;

public class ShapeSetting extends ComboSetting {
    public ShapeSetting(String text, double height, double labelPercent){
        super(text, height, labelPercent);


        addValue(Rectangle.class);
        addValue(Circle.class);
        addValue(Ellipse.class);
        addValue(Line.class);
        addValue(Text.class);
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
    }
}
