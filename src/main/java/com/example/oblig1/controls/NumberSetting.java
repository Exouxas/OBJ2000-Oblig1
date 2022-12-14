package com.example.oblig1.controls;

import com.example.oblig1.DrawStructure;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.TextField;

public class NumberSetting extends CustomSetting {
    private TextField field;
    private EventHandler<ActionEvent> handler;


    public double getValue(){
        return Double.parseDouble(field.getText());
    }
    public void setValue(double value){
        field.setText("" + value);
    }


    public NumberSetting(String text, DrawStructure structure){
        super(text, structure, new TextField("0.0"));

        field = (TextField)control;

        field.textProperty().addListener((observable, oldValue, newValue) -> {
            try{ // input validation
                Double.parseDouble(newValue);
                field.setStyle("");
                if(handler != null){
                    ActionEvent e = new ActionEvent(this, this);

                    handler.handle(e);
                }
            }catch(NumberFormatException e){
                field.setStyle("-fx-background-color: red;");
            }
        });  // Add event to verify content of setting. If valid set background white, if invalid set background red ish.
    }

    public void setOnAction(EventHandler<ActionEvent> value){
        handler = value;
    }
}
