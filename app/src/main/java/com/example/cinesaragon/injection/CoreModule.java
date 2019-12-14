package com.example.cinesaragon.injection;

import android.content.Context;
import android.location.LocationManager;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreSettings;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module(injects = {}, includes = {ApplicationModule.class}, library = true)
public class CoreModule {

    @Provides
    public FirebaseAuth provideAuthenticationService() {
        return FirebaseAuth.getInstance();
    }

    @Provides
    public FirebaseFirestore provideDatabaseInstance() {
        final FirebaseFirestoreSettings settings = new FirebaseFirestoreSettings.Builder()
                .setPersistenceEnabled(true)
                .build();
        final FirebaseFirestore instance = FirebaseFirestore.getInstance();
        instance.setFirestoreSettings(settings);
        return instance;
    }

    @Provides
    public LocationManager provideLocationManager(Context context) {
        return (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
    }

    @Provides
    @Singleton
    public ThreadPoolExecutor provideExecutor() {
        return new ThreadPoolExecutor(Runtime.getRuntime().availableProcessors(), Runtime.getRuntime().availableProcessors(),
                120, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>());
    }
}
