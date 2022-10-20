package com.example.oblig1.controls;

import javafx.event.EventHandler;
import javafx.scene.control.ComboBox;
import javafx.util.StringConverter;

public class ComboSetting extends CustomSetting {
    protected ComboBox<Object> box;


    public Object getValue(){
        return box.getValue();
    }
    public void setValue(int value){
        box.getSelectionModel().select(box.getItems().get(value));
    }
    public void addValue(Object o){
        box.getItems().add(o);
        setValue(0);
    }


    public ComboSetting(String text, double height, double labelPercent){
        super(text, height, labelPercent, new ComboBox<Object>());

        box = (ComboBox)control;
    }

    public void setOnAction(EventHandler e){
        box.setOnAction(e);
    }

    public void setConverter(StringConverter<Object> converter){
        box.setConverter(converter);
    }
}
