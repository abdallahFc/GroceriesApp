package com.example.finalproject.data.network.produect;

import com.example.finalproject.model.ProducetModel;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.Path;

public interface ProducetInterface {
    @GET("products")
    Observable<List<ProducetModel>> getAllData();
    @GET("products?limit=5")
    Observable<List<ProducetModel>> getsomeData();
    @GET("products/{id}")
    Observable<ProducetModel> getProduect(@Path("id") int id);
    @GET("products/categories")
    Observable<ArrayList<String>> getCatagory();
    @GET("products/category/{category}")
    Observable<List<ProducetModel>> getAllDataFromCatagory(@Path("category") String category);
}
