package com.mycompany.devinaekawati.suitmediatest;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Devina Ekawati on 1/7/2017.
 */

public class ApiClient {
    public static final String BASE_URL = "http://dry-sierra-6832.herokuapp.com/api/";
    private static Retrofit retrofit = null;

    public static Retrofit getClient() {
        if (retrofit==null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
