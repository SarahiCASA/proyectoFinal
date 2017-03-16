package com.example.sistemascasa.tigie.rest.adapter;

import com.example.sistemascasa.tigie.rest.EndpointsService;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class RestApiAdapter {
    public static final String BASE_URL = "http://10.40.68.153:8085/";
    public EndpointsService establecerConexionRestApi () {


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(EndpointsService.class);

    }

}
