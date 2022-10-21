package com.example.finalproject.viewModel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.finalproject.model.ProducetModel;
import com.example.finalproject.repo.ProduectRepo;
import com.example.finalproject.repo.UserRepo;

import java.util.ArrayList;
import java.util.List;

public class ProduectViewModel extends ViewModel {
    private final ProduectRepo repo = new ProduectRepo();
    public void getAllProduect(){
        repo.getAllProduect();
    }
    public MutableLiveData<List<ProducetModel>> getProduect(){
        return repo.getProduect();
    }
    public  MutableLiveData<String> getProduectError(){
        return repo.getProduectError();
    }
    public void getFiveProduects(){
        repo.getFiveProduects();
    }
    public MutableLiveData<List<ProducetModel>> getFiveProduect(){
        return repo.getFiveProduect();
    }
    public  MutableLiveData<String> getError(){
        return repo.getError();
    }
    public void getSomeProduect(int id){
         repo.getSomeProduect(id);
    }
    public MutableLiveData<ProducetModel> getSomeProduect(){
        return repo.getSomeProduect();
    }
    public  MutableLiveData<String> getSomeProduectError(){
        return repo.getSomeProduectError();
    }
    public void getCatagory(){
        repo.getGatagory();
    }
    public MutableLiveData <ArrayList<String>> getCatagorys(){
        return repo.getCatagorys();
    }
    public  MutableLiveData<String> getCatagoryError(){
        return repo.getCatagoryError();
    }
}
