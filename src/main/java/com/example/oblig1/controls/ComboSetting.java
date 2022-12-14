package com.example.oblig1.controls;

import com.example.oblig1.DrawStructure;
import javafx.event.EventHandler;
import javafx.scene.control.ComboBox;
import javafx.scene.control.SingleSelectionModel;
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


    public ComboSetting(String text, DrawStructure structure){
        super(text, structure, new ComboBox<Object>());

        box = (ComboBox)control;
    }

    public void setOnAction(EventHandler e){
        box.setOnAction(e);
    }

    public SingleSelectionModel<Object> getSelectionModel(){return box.getSelectionModel();}

    public void setConverter(StringConverter<Object> converter){
        box.setConverter(converter);
    }
}
