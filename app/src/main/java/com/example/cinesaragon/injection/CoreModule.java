package com.example.cinesaragon.injection;

import android.content.Context;
import android.location.LocationManager;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

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
        return FirebaseFirestore.getInstance();
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
