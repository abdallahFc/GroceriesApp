package com.example.finalproject.data.network.user;


import com.example.finalproject.model.UserResponse;

import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory;
import io.reactivex.rxjava3.core.Observable;
import retrofit2.Retrofit;

import retrofit2.converter.gson.GsonConverterFactory;


public class UserClient {
    private static final String BASE_URL = "https://bego8889.000webhostapp.com/php/";
    private static UserClient INSTANCE;
    private UserInterface userInterface;

    public UserClient() {
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build();
        userInterface=retrofit.create(UserInterface.class);
    }
    public static UserClient getInstance(){
        if(INSTANCE==null){
            INSTANCE=new UserClient();
        }
        return INSTANCE;
    }
    public Observable<UserResponse> signUp(String name, String email, String password) {
        return userInterface.signUp(name, email, password);
    }
    public Observable<UserResponse> signIn(String email, String password) {
        return userInterface.signIn(email, password);
    }
}
