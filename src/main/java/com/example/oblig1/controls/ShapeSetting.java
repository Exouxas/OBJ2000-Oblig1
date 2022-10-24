package com.example.oblig1.controls;

import com.example.oblig1.DrawableShapes.*;
import javafx.scene.text.Text;
import javafx.util.StringConverter;

public class ShapeSetting extends ComboSetting {
    public ShapeSetting(String text, double height, double labelPercent){
        super(text, height, labelPercent);


        addValue(DrawableRectangle.class);
        addValue(DrawableCircle.class);
        addValue(DrawableEllipse.class);
        addValue(DrawableLine.class);
        addValue(DrawableText.class);
        this.setConverter(new StringConverter<Object>(){
            @Override
            public String toString(Object obj){
                return ((IDrawable)obj).getName();
            }

            @Override
            public Object fromString(String s){
                return null;
            }
        });
    }
}
