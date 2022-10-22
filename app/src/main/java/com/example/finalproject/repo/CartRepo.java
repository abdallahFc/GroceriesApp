package com.example.finalproject.repo;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;

import com.example.finalproject.data.local.room.Cart;
import com.example.finalproject.data.local.room.CartDataBase;
import com.example.finalproject.model.ProducetModel;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.CompletableObserver;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class CartRepo {
    private final MutableLiveData<String> insert = new MutableLiveData<>();
    private final MutableLiveData<String> delete = new MutableLiveData<>();
    private final MutableLiveData<String> deleteError = new MutableLiveData<>();
    private final MutableLiveData<List<Cart>> data = new MutableLiveData<>();
    private final MutableLiveData<String> insertErorr = new MutableLiveData<>();
    private final MutableLiveData<String> dataErorr = new MutableLiveData<>();
    public void insert(Cart cart, Context context){
        CartDataBase.getInstance(context).Doa().insertItem(cart).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new CompletableObserver() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onComplete() {
                insert.setValue("Item inserted");
            }

            @Override
            public void onError(@NonNull Throwable e) {
                insertErorr.setValue(e.toString());
            }
        });
    }
    public void delete(int id, Context context){
        CartDataBase.getInstance(context).Doa().deleteItem(id).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new CompletableObserver() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onComplete() {
                        delete.setValue("Item Remove");
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        deleteError.setValue(e.toString());
                    }
                });

    }
    public void getAll(Context context){
        CartDataBase.getInstance(context).Doa().getAllItems().subscribeOn(Schedulers.computation()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<Cart>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull List<Cart> carts) {
                        data.setValue(carts);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        dataErorr.setValue(e.toString());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
    public MutableLiveData<List<Cart>> getItems(){
        return data;
    }
    public MutableLiveData<String> getmsg(){
        return insert;
    }
    public MutableLiveData<String> getMsgDelete(){
        return delete;
    }
    public MutableLiveData<String> getInsertErorr(){
        return insertErorr;
    }
    public MutableLiveData<String> getDataErorr(){
        return dataErorr;
    }
    public MutableLiveData<String> getDeleteErorr(){
        return deleteError;
    }

}
