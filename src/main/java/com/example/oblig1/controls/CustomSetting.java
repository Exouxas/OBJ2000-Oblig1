package com.example.oblig1.controls;

import com.example.oblig1.DrawStructure;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;

public class CustomSetting extends GridPane {
    protected Label label;
    public Control control;

    public CustomSetting(String text, DrawStructure structure, Control control){
        this.control = control;

        this.getRowConstraints().add(new RowConstraints(structure.getSettingHeight()));

        ColumnConstraints labelColumn = new ColumnConstraints();
        labelColumn.setPercentWidth(structure.getSettingRatio());
        this.getColumnConstraints().add(labelColumn);


        label = new Label(text);
        label.setMinHeight(structure.getSettingHeight());
        label.setPrefWidth(9999);
        this.add(label, 0, 0);

        this.add(control, 1, 0);
        control.setMinHeight(structure.getSettingHeight());
        control.setPrefWidth(9999);
    }
}
