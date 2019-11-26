package com.example.cinesaragon.injection;

import android.content.Context;

import dagger.Module;
import dagger.Provides;

@Module(complete = false, library = true)
public class ApplicationModule {

    private final Context context;

    public ApplicationModule(Context context) {
        this.context = context;
    }

    @Provides
    public Context provideApplicationContext() {
        return context;
    }
}
