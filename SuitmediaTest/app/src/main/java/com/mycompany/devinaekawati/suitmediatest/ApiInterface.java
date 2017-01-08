package com.mycompany.devinaekawati.suitmediatest;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Devina Ekawati on 1/7/2017.
 */

public interface ApiInterface {
    @GET("people")
    Call<List<Guest>> getGuests();
}
