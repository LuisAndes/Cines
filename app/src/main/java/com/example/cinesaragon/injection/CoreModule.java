package com.example.cinesaragon.injection;

import com.example.cinesaragon.MainActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import dagger.Module;
import dagger.Provides;

@Module(injects = {MainActivity.class}, library = true)
public class CoreModule {

    @Provides
    public FirebaseAuth provideAuthenticationService() {
        return FirebaseAuth.getInstance();
    }

    @Provides
    public FirebaseFirestore provideDatabaseInstance() {
        return FirebaseFirestore.getInstance();
    }
}
