package com.example.finalproject.data.network.produect;

import com.example.finalproject.model.ProducetModel;

import java.util.ArrayList;
import java.util.List;

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
                .build();
        producetInterface = retrofit.create(ProducetInterface.class);
    }

    public static ProducetClient getInstance() {
        if (INSTANCE == null)
            INSTANCE = new ProducetClient();
        return INSTANCE;
    }

    public Call<List<ProducetModel>> getAllData() {
        return producetInterface.getAllData();
    }

    public Call<List<ProducetModel>> getsomeData() {
        return producetInterface.getsomeData();
    }

    public Call<ArrayList<String>> getCatagory() {
        return producetInterface.getCatagory();
    }

    //    public Call<List<ProducetModel>> getAllDataFromCatagory(String){
//        return producetInterface.getAllDataFromCatagory();
//    }
    public Call<ProducetModel> getProduect(int id) {
        return producetInterface.getProduect(id);
    }
}
