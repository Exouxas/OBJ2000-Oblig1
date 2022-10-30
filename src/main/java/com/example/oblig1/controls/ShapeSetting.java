package com.example.oblig1.controls;

import com.example.oblig1.DrawStructure;
import com.example.oblig1.DrawableShapes.*;
import javafx.util.StringConverter;

public class ShapeSetting extends ComboSetting {
    public ShapeSetting(String text, DrawStructure structure){
        super(text, structure);


        addValue(new NamedDrawable(new DrawableRectangle(structure)));
        addValue(new NamedDrawable(new DrawableCircle(structure)));
        addValue(new NamedDrawable(new DrawableEllipse(structure)));
        addValue(new NamedDrawable(new DrawableLine(structure)));
        addValue(new NamedDrawable(new DrawableText(structure)));

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
