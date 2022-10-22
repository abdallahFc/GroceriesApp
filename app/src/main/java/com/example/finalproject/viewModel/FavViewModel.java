package com.example.finalproject.viewModel;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.finalproject.data.local.room.Cart;
import com.example.finalproject.data.local.room.Fav;
import com.example.finalproject.repo.CartRepo;
import com.example.finalproject.repo.FavRepo;

import java.util.List;

public class FavViewModel extends ViewModel {
    private final FavRepo repo = new FavRepo();
    public void insert(Fav fav, Context context){
        repo.insert(fav,context);
    }
    public void getAll(Context context){
        repo.getAll(context);
    }
    public void delete(Context context){
        repo.delete(context);
    }
    public MutableLiveData<List<Fav>> getItems(){
        return repo.getItems();
    }
    public MutableLiveData<String> getmsg(){
        return repo.getmsg();
    }
    public MutableLiveData<String> getMsgDelete(){
        return repo.getMsgDelete();
    }
    public MutableLiveData<String> getInsertErorr(){
        return repo.getInsertErorr();
    }
    public MutableLiveData<String> getDataErorr(){
        return repo.getDataErorr();
    }
    public MutableLiveData<String> getDeleteErorr(){
        return repo.getDeleteErorr();
    }

}
