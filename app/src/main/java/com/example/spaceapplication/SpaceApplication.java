package com.example.spaceapplication;

import android.app.Application;

import com.example.api.NasaApi;
import com.example.api.NasaService;

public class SpaceApplication extends Application {

    private NasaApi nasaService;

    @Override
    public void onCreate() {
        super.onCreate();

        nasaService = NasaService.getInstance();
    }

    public NasaApi getNasaApi() {
        return nasaService;
    }
}
