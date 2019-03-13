package com.aquar.myaquar_egypt.InternalStorage;

import com.aquar.myaquar_egypt.Model.HomeApi.ModelObjects;

import java.util.ArrayList;

/**
 * Created by aswany on 3/12/19.
 */

public class Session {

    private static Session instance;

    public static Session getInstance() {
        if (instance == null) {
            instance = new Session();
        }
        return instance;
    }

    private Session() {
    }
    //home array
    private ArrayList<ModelObjects> HomeArray;

    public ArrayList<ModelObjects> getHomeArray() {
        return HomeArray;
    }

    public void setHomeArray(ArrayList<ModelObjects> homeArray) {
        this.HomeArray = homeArray;
    }
}
