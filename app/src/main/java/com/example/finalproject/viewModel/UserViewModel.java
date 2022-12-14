package com.example.finalproject.viewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.finalproject.repo.UserRepo;

public class UserViewModel extends ViewModel {
    private final UserRepo repo = new UserRepo();
    public void getFromRepo(String name,String email,String pass){
        repo.getSignupResponse(name, email, pass);
    }
     public LiveData<String> getUserResponse(){
         return repo.getUserResponse();
     }
    public LiveData<String> getUserResponseError(){
        return repo.getUserResponseError();
    }

    public void getLoginFromRepo(String email,String pass){
        repo.getLoginResponse(email, pass);
    }
    public LiveData<String> getLoginResponse(){
        return repo.getLoginResponse();
    }
    public LiveData<String> getLoginResponseError(){
        return repo.getLoginResponseError();
    }


}
