package com.example.api;

import java.util.List;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface NasaApi {

    @GET("natural/caption")
    Single<List<Data>> getInformation();

    @GET("natural/date/{date}")
    Single<List<String>> getPhotosForDate(@Path("date") String date);

}
