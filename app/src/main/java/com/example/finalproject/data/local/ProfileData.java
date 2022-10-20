package com.example.finalproject.data.local;

import com.example.finalproject.R;
import com.example.finalproject.model.ProfileModel;

import java.util.ArrayList;

public class ProfileData {
    ArrayList<ProfileModel> list;
    public ArrayList<ProfileModel> getProData(){
        list=new ArrayList<>();
        list.add(new ProfileModel("Orders", R.drawable.ic_orders_icon ));
        list.add(new ProfileModel("My Details", R.drawable.ic_my_details_icon  ));
        list.add(new ProfileModel("Delivery Address", R.drawable.ic_delicery_address));
        list.add(new ProfileModel("Payment Methods", R.drawable.ic_payment));
        list.add(new ProfileModel("Promo Cord", R.drawable.ic_promo_cord_icon));
        list.add(new ProfileModel("Notifecations", R.drawable.ic_bell_icon));
        list.add(new ProfileModel("Help", R.drawable.ic_help_icon));
        list.add(new ProfileModel("About", R.drawable.ic_about_icon));
        return list;
    }
}
