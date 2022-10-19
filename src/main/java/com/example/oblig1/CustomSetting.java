package com.example.oblig1;

import javafx.event.EventHandler;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;

public class CustomSetting extends GridPane {
    protected Label label;
    public Control control;

    public CustomSetting(String text, double height, double labelPercent, Control control){
        this.control = control;

        this.getRowConstraints().add(new RowConstraints(height));

        ColumnConstraints labelColumn = new ColumnConstraints();
        labelColumn.setPercentWidth(labelPercent);
        this.getColumnConstraints().add(labelColumn);


        label = new Label(text);
        label.setMinHeight(height);
        label.setPrefWidth(9999);
        this.add(label, 0, 0);

        this.add(control, 1, 0);
        control.setMinHeight(height);
        control.setPrefWidth(9999);
    }
}
