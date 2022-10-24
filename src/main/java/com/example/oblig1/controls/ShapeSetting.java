package com.example.oblig1.controls;

import com.example.oblig1.DrawableShapes.*;
import javafx.util.StringConverter;

public class ShapeSetting extends ComboSetting {
    public ShapeSetting(String text, double height, double labelPercent){
        super(text, height, labelPercent);


        addValue(new NamedDrawable(new DrawableRectangle()));
        addValue(new NamedDrawable(new DrawableCircle()));
        addValue(new NamedDrawable(new DrawableEllipse()));
        addValue(new NamedDrawable(new DrawableLine()));
        addValue(new NamedDrawable(new DrawableText()));

        this.setConverter(new StringConverter<Object>(){
            @Override
            public String toString(Object obj){
                return ((NamedDrawable)obj).getName();
            }

            @Override
            public Object fromString(String s){
                return null;
            }
        });
    }
}
