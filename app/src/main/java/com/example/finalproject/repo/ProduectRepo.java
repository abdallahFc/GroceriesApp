package com.example.finalproject.repo;

import androidx.lifecycle.MutableLiveData;

import com.example.finalproject.model.ProducetModel;

public class ProduectRepo {
    private final MutableLiveData<ProducetModel> produect = new MutableLiveData<>();
    private final MutableLiveData<String> produectError = new MutableLiveData<>();
    private final MutableLiveData<String> catagory = new MutableLiveData<>();
    private final MutableLiveData<String> catagoryError = new MutableLiveData<>();

    public void getAllProduect(){

    }
}
