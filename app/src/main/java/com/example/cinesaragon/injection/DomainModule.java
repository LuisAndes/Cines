package com.example.cinesaragon.injection;

import com.example.cinesaragon.MainActivity;
import com.example.cinesaragon.PantallaLogin;
import com.example.cinesaragon.RegUsrScr;
import com.example.cinesaragon.UserScr;
import com.example.cinesaragon.domain.CreateUser;
import com.example.cinesaragon.domain.GetBillboard;
import com.example.cinesaragon.domain.GetCinemas;
import com.example.cinesaragon.domain.GetCurrentUser;
import com.example.cinesaragon.domain.GetSessions;
import com.example.cinesaragon.domain.GetSessionsByCinemaName;
import com.example.cinesaragon.domain.GetSessionsByDuration;
import com.example.cinesaragon.domain.GetSessionsByGenre;
import com.example.cinesaragon.domain.GetSessionsByHour;
import com.example.cinesaragon.domain.GetSessionsByMovieName;
import com.example.cinesaragon.domain.LoginUser;
import com.example.cinesaragon.domain.ModifyCurrentUser;
import com.example.cinesaragon.domain.RegisterUser;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.concurrent.ThreadPoolExecutor;

import dagger.Module;
import dagger.Provides;

@Module(injects = {PantallaLogin.class,
        RegUsrScr.class,
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

    @Provides
    public GetSessions provideGetSessions(ThreadPoolExecutor executor,
                                          FirebaseFirestore database) {
        return new GetSessions(executor, database);
    }

    @Provides
    public GetBillboard provideGetBillboard(ThreadPoolExecutor executor,
                                            GetSessions getSessions) {
        return new GetBillboard(executor, getSessions);
    }

    @Provides
    public GetSessionsByMovieName provideGetSessionsByMovieName(ThreadPoolExecutor executor,
                                                                GetSessions getSessions) {
        return new GetSessionsByMovieName(executor, getSessions);
    }

    @Provides
    public GetSessionsByCinemaName provieGetSessionsByCinemaName(ThreadPoolExecutor executor,
                                                                 GetSessions getSessions) {
        return new GetSessionsByCinemaName(executor, getSessions);
    }

    @Provides
    public GetSessionsByDuration provideGetSessionsByDuration(ThreadPoolExecutor executor,
                                                              GetSessions getSessions) {
        return new GetSessionsByDuration(executor, getSessions);
    }

    @Provides
    public GetSessionsByHour provideGetSessionsByHour(ThreadPoolExecutor executor,
                                                      GetSessions getSessions) {
        return new GetSessionsByHour(executor, getSessions);
    }

    @Provides
    public GetSessionsByGenre provideGetSessionsByGenre(ThreadPoolExecutor executor,
                                                      GetSessions getSessions) {
        return new GetSessionsByGenre(executor, getSessions);
    }
}
