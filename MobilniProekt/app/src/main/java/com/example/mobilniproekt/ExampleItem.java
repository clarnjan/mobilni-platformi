package com.example.mobilniproekt;

import java.util.ArrayList;

public class ExampleItem {
    private int mImageResource;
    private String mText1;
    private String mText2;
    private  ArrayList<String> listaVezbi;

    public ExampleItem(int mImageResource, String mText1, String mText2, ArrayList<String> x) {
        this.mImageResource = mImageResource;
        this.mText1 = mText1;
        this.mText2 = mText2;
        listaVezbi=x;
    }

    public int getmImageResource() {
        return mImageResource;
    }

    public String getmText1() {
        return mText1;
    }

    public String getmText2() {
        return mText2;
    }

    public ArrayList<String> getListaVezbi() {
        return listaVezbi;
    }
}
