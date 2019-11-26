package com.example.cinesaragon.injection;

import com.example.cinesaragon.data.CinemaService;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module(complete = true, library = true)
public class NetworkModule {

    @Provides
    public CinemaService provideCinemaService() {
        return new Retrofit.Builder()
                .baseUrl("https://cines-aragon-13e98.firebaseio.com/")
                .build()
                .create(CinemaService.class);
    }
}
