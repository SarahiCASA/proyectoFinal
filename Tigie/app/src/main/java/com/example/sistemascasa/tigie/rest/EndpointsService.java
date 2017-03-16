package com.example.sistemascasa.tigie.rest;

import com.example.sistemascasa.tigie.pojo.AndroidUsers;
import com.example.sistemascasa.tigie.pojo.Annexes;
import com.example.sistemascasa.tigie.pojo.BorderStrip;
import com.example.sistemascasa.tigie.pojo.CompensatoryShares;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.PUT;
import retrofit2.http.Query;

public interface EndpointsService {
    @GET("validateemail")
    Call<List<AndroidUsers>> validateEmail(@Query("email") String email);

    @GET("getandroiduser")
    Call<List<AndroidUsers>> getandroiduser(@Query("name") String name, @Query("password") String password, @Query("email") String email, @Query("ocupacion") String ocupacion);


    @GET("validateuser")
    Call<List<AndroidUsers>> validateUser(@Query("emailLogin") String emailLogin, @Query("passwordLogin") String passwordLogin);

    @GET("getcompensatoryshares")
    Call<List<CompensatoryShares>> getcompensatoryshares(@Query("fraccion") String fraccion);

    @GET("getborderstrip")
    Call<List<BorderStrip>> getborderstrip(@Query("fraccion") String fraccion);

    @GET("getannexes")
    Call<List<Annexes>> getannexes(@Query("fraccion") String fraccion);

}
