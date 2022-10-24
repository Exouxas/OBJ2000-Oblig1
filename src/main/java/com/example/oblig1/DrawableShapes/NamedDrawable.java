package com.example.oblig1.DrawableShapes;

public class NamedDrawable {
    private IDrawable drawable;
    public IDrawable getDrawable(){return drawable;}
    public String getName(){return drawable.getName();}

    public NamedDrawable(IDrawable drawable){
        this.drawable = drawable;
    }
}
