package com.example.finalproject.repo;

import androidx.lifecycle.MutableLiveData;

import com.example.finalproject.data.network.produect.ProducetClient;
import com.example.finalproject.model.ProducetModel;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class ProduectRepo {
    private final MutableLiveData<List<ProducetModel>> produect = new MutableLiveData<>();
    private final MutableLiveData<List<ProducetModel>> oneProduect = new MutableLiveData<>();
    private final MutableLiveData<ProducetModel> someProduect = new MutableLiveData<>();
    private final MutableLiveData<String> produectError = new MutableLiveData<>();
    private final MutableLiveData<String> someProduectError = new MutableLiveData<>();
    private final MutableLiveData<String> oneProduectError = new MutableLiveData<>();
    private final MutableLiveData<ArrayList<String>>  catagory = new MutableLiveData<>();
    private final MutableLiveData<String> catagoryError = new MutableLiveData<>();

    public void getAllProduect(){
        Observable<List<ProducetModel>> observable= ProducetClient.getInstance().getAllData()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());
        Observer<List<ProducetModel>> observer=new Observer<List<ProducetModel>>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull List<ProducetModel> producetModels) {
                produect.setValue(producetModels);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                produectError.setValue(e.toString());
            }

            @Override
            public void onComplete() {

            }
        };
        observable.subscribe(observer);
    }
    public MutableLiveData<List<ProducetModel>> getProduect(){
        return produect;
    }
    public  MutableLiveData<String> getProduectError(){
        return produectError;
    }
    public void getFiveProduects(){
        Observable<List<ProducetModel>> observable= ProducetClient.getInstance().getsomeData()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());
        Observer<List<ProducetModel>> observer=new Observer<List<ProducetModel>>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull List<ProducetModel> producetModels) {
                oneProduect.setValue(producetModels);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                oneProduectError.setValue(e.toString());
            }

            @Override
            public void onComplete() {

            }
        };
        observable.subscribe(observer);
    }
    public MutableLiveData<List<ProducetModel>> getFiveProduect(){
        return oneProduect;
    }
    public  MutableLiveData<String> getError(){
        return oneProduectError;
    }
    public void getSomeProduect(int id){
        Observable<ProducetModel> observable=ProducetClient.getInstance().getProduect(id)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());
        Observer <ProducetModel> observer=new Observer<ProducetModel>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull ProducetModel producetModel) {
                someProduect.setValue(producetModel);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                someProduectError.setValue(e.toString());
            }

            @Override
            public void onComplete() {

            }
        };
        observable.subscribe(observer);
    }
    public MutableLiveData<ProducetModel> getSomeProduect(){
        return someProduect;
    }
    public  MutableLiveData<String> getSomeProduectError(){
        return someProduectError;
    }
    public void getGatagory(){
        Observable<ArrayList<String>> observable=ProducetClient.getInstance().getCatagory()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());
        Observer <ArrayList<String>>  observer=new Observer<ArrayList<String>>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull ArrayList<String> strings) {
                catagory.setValue(strings);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                catagoryError.setValue(e.toString());
            }

            @Override
            public void onComplete() {

            }
        };
        observable.subscribe(observer);
    }
    public MutableLiveData <ArrayList<String>> getCatagorys(){
        return catagory;
    }
    public  MutableLiveData<String> getCatagoryError(){
        return catagoryError;
    }

}
