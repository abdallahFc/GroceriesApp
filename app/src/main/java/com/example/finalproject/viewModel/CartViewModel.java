package com.example.finalproject.viewModel;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.finalproject.data.local.room.Cart;
import com.example.finalproject.repo.CartRepo;
import com.example.finalproject.repo.ProduectRepo;

import java.util.List;

public class CartViewModel extends ViewModel {
    private final CartRepo repo = new CartRepo();
    public void insert(Cart cart, Context context){
        repo.insert(cart,context);
    }
    public void getAll(Context context){
        repo.getAll(context);
    }
    public void delete(int id, Context context){
        repo.delete(id,context);
    }
    public MutableLiveData<List<Cart>> getItems(){
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
