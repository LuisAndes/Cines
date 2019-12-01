package com.example.cinesaragon.injection;

import com.example.cinesaragon.MainActivity;
import com.example.cinesaragon.PantallaLogin;
import com.example.cinesaragon.UserScr;
import com.example.cinesaragon.domain.CreateUser;
import com.example.cinesaragon.domain.GetCinemas;
import com.example.cinesaragon.domain.GetCurrentUser;
import com.example.cinesaragon.domain.LoginUser;
import com.example.cinesaragon.domain.ModifyCurrentUser;
import com.example.cinesaragon.domain.RegisterUser;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.concurrent.ThreadPoolExecutor;

import dagger.Module;
import dagger.Provides;

@Module(injects = {PantallaLogin.class,
        MainActivity.class,
        UserScr.class}, complete = false, library = true)
public class DomainModule {

    @Provides
    public CreateUser provideCreateUser(ThreadPoolExecutor executor,
                                        FirebaseFirestore database) {
        return new CreateUser(executor, database);
    }

    @Provides
    public RegisterUser provideRegisterUser(ThreadPoolExecutor executor,
                                            FirebaseAuth authentication,
                                            CreateUser createUser) {
        return new RegisterUser(executor, authentication, createUser);
    }

    @Provides
    public LoginUser provideLoginUser(ThreadPoolExecutor executor,
                                      FirebaseAuth authentication) {
        return new LoginUser(executor, authentication);
    }

    @Provides
    public GetCurrentUser provideCurrentUser(ThreadPoolExecutor executor,
                                             FirebaseAuth authentication,
                                             FirebaseFirestore database) {
        return new GetCurrentUser(executor, authentication, database);
    }

    @Provides
    public ModifyCurrentUser provideModifyCurrentUser(ThreadPoolExecutor executor,
                                                      FirebaseAuth authentication,
                                                      FirebaseFirestore database,
                                                      CreateUser createUser) {
        return new ModifyCurrentUser(executor, authentication, database, createUser);
    }

    @Provides
    public GetCinemas provideUnregisteredCinemas(ThreadPoolExecutor executor,
                                                 FirebaseFirestore database) {
        return new GetCinemas(executor, database);
    }
}
