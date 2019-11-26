package com.example.cinesaragon.application;

import android.app.Application;

import com.example.cinesaragon.injection.ApplicationModule;
import com.example.cinesaragon.injection.CoreModule;
import com.example.cinesaragon.injection.NetworkModule;
import com.google.firebase.FirebaseApp;

import dagger.ObjectGraph;

public class CinesApplication extends Application {

    private ObjectGraph objectGraph;

    @Override
    public void onCreate() {
        super.onCreate();
        FirebaseApp.initializeApp(this);
        objectGraph = initializeGraph();
    }

    ObjectGraph initializeGraph() {
        return ObjectGraph.create(new ApplicationModule(this),
                new NetworkModule(),
                new CoreModule());
    }

    public <T> void inject(T instance) {
        objectGraph.inject(instance);
    }
}
