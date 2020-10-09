package com.walter.covid19app.API;

import com.google.gson.annotations.SerializedName;
import com.walter.covid19app.Model.Countries;
import com.walter.covid19app.Model.Global;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface InterfaceAPI {
    @GET("countries")
    Call<List<Countries>> getCountries(@Query("sort") String sort);

    @GET("all")
    Call<Global> getInfoGlobal();

    @GET("all/{cases}")
    Call<Global> getAllCases(@Path("cases") int cases);

}
