package com.example.api;

import retrofit2.Retrofit;

public class NasaService {

    private static NasaApi api;

    private NasaService() {}

    public static NasaApi getInstance() {
        if (api == null) {
            Retrofit retrofit = RetrofitFactory.createRetrofit(HttpClient.getInstance());
            api = retrofit.create(NasaApi.class);
        }
        return api;
    }

}
