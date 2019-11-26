package com.example.cinesaragon.injection;

import com.example.cinesaragon.PantallaLogin;
import com.google.firebase.auth.FirebaseAuth;

import dagger.Module;
import dagger.Provides;

@Module(injects = {PantallaLogin.class}, library = true)
public class CoreModule {

    @Provides
    public FirebaseAuth provideAuthenticationService() {
        return FirebaseAuth.getInstance();
    }
}
