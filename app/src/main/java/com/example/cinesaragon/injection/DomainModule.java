package com.example.cinesaragon.injection;

import com.example.cinesaragon.PantallaLogin;
import com.example.cinesaragon.domain.GetCurrentUser;
import com.example.cinesaragon.domain.LoginUser;
import com.example.cinesaragon.domain.RegisterUser;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;

import dagger.Module;
import dagger.Provides;

@Module(injects = {PantallaLogin.class}, complete = false, library = true)
public class DomainModule {

    @Provides
    public RegisterUser provideRegisterUser(FirebaseAuth authentication,
                                            DatabaseReference database) {
        return new RegisterUser(authentication, database);
    }

    @Provides
    public LoginUser provideLoginUser(FirebaseAuth authentication) {
        return new LoginUser(authentication);
    }

    @Provides
    public GetCurrentUser provideCurrentUser(FirebaseAuth authentication,
                                             DatabaseReference database) {
        return new GetCurrentUser(authentication, database);
    }
}
