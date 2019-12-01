package com.example.cinesaragon.injection;

import android.content.Context;
import android.location.LocationManager;

import com.example.cinesaragon.MainActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import dagger.Module;
import dagger.Provides;

@Module(injects = {}, includes = {ApplicationModule.class} , library = true)
public class CoreModule {

    @Provides
    public FirebaseAuth provideAuthenticationService() {
        return FirebaseAuth.getInstance();
    }

    @Provides
    public FirebaseFirestore provideDatabaseInstance() {
        return FirebaseFirestore.getInstance();
    }

    @Provides
    public LocationManager provideLocationManager(Context context) {
        return (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
    }
}
