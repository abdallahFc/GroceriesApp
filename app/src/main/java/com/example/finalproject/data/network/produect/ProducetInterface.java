package com.example.finalproject.data.network.produect;

import com.example.finalproject.model.ProducetModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.Path;

public interface ProducetInterface {
    @GET("products")
    Call<List<ProducetModel>> getAllData();
    @GET("products?limit=5")
    Call<List<ProducetModel>> getsomeData();
    @GET("products/{id}")
    Call<ProducetModel> getProduect(@Path("id") int id);
    @GET("products/categories")
    Call<ArrayList<String>> getCatagory();
    @GET("products/category/{category}")
    Call<List<ProducetModel>> getAllDataFromCatagory(@Path("category") String category);
}
