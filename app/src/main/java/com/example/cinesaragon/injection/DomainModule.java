package com.example.cinesaragon.injection;

import com.example.cinesaragon.PantallaLogin;
import com.example.cinesaragon.UserScr;
import com.example.cinesaragon.domain.CreateUser;
import com.example.cinesaragon.domain.GetCurrentUser;
import com.example.cinesaragon.domain.GetUnregisteredCinemas;
import com.example.cinesaragon.domain.LoginUser;
import com.example.cinesaragon.domain.ModifyCurrentUser;
import com.example.cinesaragon.domain.RegisterUser;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import dagger.Module;
import dagger.Provides;

@Module(injects = {PantallaLogin.class,
        UserScr.class}, complete = false, library = true)
public class DomainModule {

    @Provides
    public CreateUser provideCreateUser(FirebaseFirestore database) {
        return new CreateUser(database);
    }

    @Provides
    public RegisterUser provideRegisterUser(FirebaseAuth authentication,
                                            CreateUser createUser) {
        return new RegisterUser(authentication, createUser);
    }

    @Provides
    public LoginUser provideLoginUser(FirebaseAuth authentication) {
        return new LoginUser(authentication);
    }

    @Provides
    public GetCurrentUser provideCurrentUser(FirebaseAuth authentication,
                                             FirebaseFirestore database) {
        return new GetCurrentUser(authentication, database);
    }

    @Provides
    public ModifyCurrentUser provideModifyCurrentUser(FirebaseAuth authentication,
                                                      FirebaseFirestore database,
                                                      CreateUser createUser) {
        return new ModifyCurrentUser(authentication, database, createUser);
    }

    @Provides
    public GetUnregisteredCinemas provideUnregisteredCinemas(FirebaseFirestore database) {
        return new GetUnregisteredCinemas(database);
    }
}
