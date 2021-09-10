package com.example.recipes;

import java.util.ArrayList;

public class ExampleItem {
    private int mImageResource;
    private String mText1;
    private String getmText2,getmText3;
    private ArrayList<String> lista;
    private ArrayList<String> steps;

    public ExampleItem(int mImageResource, String mText1, String getmText2,String getmText3, ArrayList<String> lista, ArrayList<String> steps) {
        this.mImageResource = mImageResource;
        this.mText1 = mText1;
        this.getmText2 = getmText2;
        this.getmText3 = getmText3;
        this.lista=lista;
        this.steps=steps;

    }

    public ArrayList<String> getSteps() {
        return steps;
    }

    public int getmImageResource() {
        return mImageResource;
    }

    public String getmText1() {
        return mText1;
    }

    public String getmText2() {
        return getmText2;
    }
    public String getmText3() {
        return getmText3;
    }

    public ArrayList<String> getLista() {
        return lista;
    }
}
