package com.example.oblig1.controls;

import javafx.scene.control.TextField;

public class NumberSetting extends CustomSetting {
    private TextField field;


    public double getValue(){
        return Double.parseDouble(field.getText());
    }
    public void setValue(double value){
        field.setText("" + value);
    }


    public NumberSetting(String text, double height, double labelPercent){
        super(text, height, labelPercent, new TextField("0.0"));

        field = (TextField)control;

        field.textProperty().addListener((observable, oldValue, newValue) -> {
            try{ // input validation
                Double.parseDouble(newValue);
                field.setStyle("");
            }catch(NumberFormatException e){
                field.setStyle("-fx-background-color: red;");
            }
        });  // Add event to verify content of setting. If valid set background white, if invalid set background red ish.
    }
}
