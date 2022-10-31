package com.example.oblig1;

import com.example.oblig1.controls.CustomSetting;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

import java.util.LinkedList;

public class ZOrderManager {
    DrawStructure structure;
    Node node;

    public ZOrderManager(Node node, VBox settings, DrawStructure structure){
        this.structure = structure;
        this.node = node;

        Button front = new Button("To front");
        CustomSetting frontSetting = new CustomSetting("To front:", structure, front);
        settings.getChildren().add(frontSetting);
        front.setOnAction(e -> {
            node.toFront();
        });

        Button fwd = new Button("Forward");
        CustomSetting fwdSetting = new CustomSetting("Forward:", structure, fwd);
        settings.getChildren().add(fwdSetting);
        fwd.setOnAction(e -> {
            zOrder(1);
        });

        Button bwd = new Button("Backward");
        CustomSetting bwdSetting = new CustomSetting("Backward:", structure, bwd);
        settings.getChildren().add(bwdSetting);
        bwd.setOnAction(e -> {
            zOrder(-1);
        });

        Button back = new Button("To back");
        CustomSetting backSetting = new CustomSetting("To back:", structure, back);
        settings.getChildren().add(backSetting);
        back.setOnAction(e -> {
            node.toBack();
        });
    }

    private void zOrder(int positionToMove){ // Doesn't function as expected, but should be close to functioning
        LinkedList<Node> l1 = new LinkedList<>();
        LinkedList<Node> l2 = new LinkedList<>();
        ObservableList<Node> l = structure.getDrawArea().getChildren();

        boolean found = false;
        for(Node n : l){
            if(found){
                l2.add(n);
            }else{
                if(n == node){
                    found = true;
                }else{
                    l1.add(n);
                }
            }
        }

        for(int i = 0; i < Math.abs(positionToMove); i++){
            if(positionToMove > 0){
                if(l2.size() > 0){
                    Node n = l2.getFirst();
                    l1.add(n);
                    l2.remove(n);
                }
            } else if (positionToMove < 0) {
                if(l1.size() > 0) {
                    Node n = l1.getLast();
                    l2.addFirst(n);
                    l1.remove(n);
                }
            }
        }

        for(int i = l1.size() - 1; i >= 0; i--){
            l1.get(i).toBack();
        }

        for(int i = 0; i < l2.size(); i++){
            l2.get(i).toFront();
        }

        structure.getBackground().toBack();
    }
}
