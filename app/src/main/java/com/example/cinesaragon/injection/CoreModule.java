package com.example.cinesaragon.injection;

import com.example.cinesaragon.MainActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import dagger.Module;
import dagger.Provides;

@Module(injects = {MainActivity.class}, library = true)
public class CoreModule {

    @Provides
    public FirebaseAuth provideAuthenticationService() {
        return FirebaseAuth.getInstance();
    }

    @Provides
    public DatabaseReference provideDatabaseInstance() {
        return FirebaseDatabase.getInstance().getReference();
    }
}
