package com.example.assignment_2;

import android.app.Application;

public class myApp extends Application {

    private Store_Manager manager =  new Store_Manager();

    public Store_Manager getManager() {
        return manager;
    }

}
