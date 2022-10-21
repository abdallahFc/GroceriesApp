package com.example.finalproject.repo;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.finalproject.data.network.user.UserClient;
import com.example.finalproject.model.UserResponse;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;


public class UserRepo {
    private final MutableLiveData<String> signinResponse = new MutableLiveData<>();
    private final MutableLiveData<String> loginResponse = new MutableLiveData<>();
    private final MutableLiveData<String> error = new MutableLiveData<>();
    private final MutableLiveData<String> loginError = new MutableLiveData<>();

    public void getSignupResponse(String name, String email, String pass) {
        Observable<UserResponse> observable= UserClient.getInstance().signUp(name, email, pass)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());
        Observer<UserResponse> observer= new Observer<UserResponse>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull UserResponse userResponse) {
                Log.d("Main","ahmed "+userResponse.getResponse());
                if (userResponse.getResponse().equals("هذا البريد مسجل من قبل"))
                    error.setValue(userResponse.getResponse());
                else {
                    signinResponse.setValue(userResponse.getResponse());
                }
            }

            @Override
            public void onError(@NonNull Throwable e) {
                signinResponse.setValue(e.toString());
            }

            @Override
            public void onComplete() {

            }
        };
        observable.subscribe(observer);
    }
    public void getLoginResponse(String email,String pass){
        Observable<UserResponse> observable=UserClient.getInstance().signIn(email,pass)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());
        Observer<UserResponse> observer=new Observer<UserResponse>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull UserResponse userResponse) {
                if (userResponse.getResponse().equals("تأكد من البريد او الرقم السرى")){
                    loginError.setValue(userResponse.getResponse());
                }
                else{
                    loginResponse.setValue(userResponse.getResponse());
                }

            }

            @Override
            public void onError(@NonNull Throwable e) {
                loginError.setValue(e.toString());
            }

            @Override
            public void onComplete() {

            }
        };
        observable.subscribe(observer);
    }

    public LiveData<String> getUserResponse() {
        return signinResponse;
    }

    public LiveData<String> getUserResponseError() {
        return error;
    }
    public LiveData<String> getLoginResponse() {
        return loginResponse;
    }

    public LiveData<String> getLoginResponseError() {
        return loginError;
    }
}
