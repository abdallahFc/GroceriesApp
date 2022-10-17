package com.example.finalproject;



import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface UserInterface {

    @FormUrlEncoded
    @POST("signup.php")
    Observable<UserResponse> signUp(@Field("name") String name,
                                    @Field("email") String email,
                                    @Field("password") String password);

    @FormUrlEncoded
    @POST("login.php")
    Observable<UserResponse> signIn(@Field("email") String email,
                                   @Field("password") String password);


}
