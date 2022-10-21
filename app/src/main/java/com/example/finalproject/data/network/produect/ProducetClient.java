package com.example.finalproject.data.network.produect;

import com.example.finalproject.model.ProducetModel;

import java.util.ArrayList;
import java.util.List;

import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory;
import io.reactivex.rxjava3.core.Observable;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Path;

public class ProducetClient {
    private static final String BASE_URL = "https://fakestoreapi.com/";
    private static ProducetClient INSTANCE;
    private ProducetInterface producetInterface;

    public ProducetClient() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build();
        producetInterface = retrofit.create(ProducetInterface.class);
    }

    public static ProducetClient getInstance() {
        if (INSTANCE == null)
            INSTANCE = new ProducetClient();
        return INSTANCE;
    }

    public Observable<List<ProducetModel>> getAllData() {
        return producetInterface.getAllData();
    }

    public Observable<List<ProducetModel>> getsomeData() {
        return producetInterface.getsomeData();
    }

    public Observable<ArrayList<String>> getCatagory() {
        return producetInterface.getCatagory();
    }

    //    public Call<List<ProducetModel>> getAllDataFromCatagory(String){
//        return producetInterface.getAllDataFromCatagory();
//    }
    public Observable<ProducetModel> getProduect(int id) {
        return producetInterface.getProduect(id);
    }
}
