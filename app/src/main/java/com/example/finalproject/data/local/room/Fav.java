package com.example.finalproject.data.local.room;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Fav_table")
public class Fav {

    @PrimaryKey(autoGenerate = true)
    private int id ;
    private String itemTitle;
    private String itemPrice;
    private String itemRate;
    private String itemImg;

    public Fav(int id, String itemTitle, String itemPrice, String itemRate, String itemImg) {
        this.id = id;
        this.itemTitle = itemTitle;
        this.itemPrice = itemPrice;
        this.itemRate = itemRate;
        this.itemImg = itemImg;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getItemTitle() {
        return itemTitle;
    }

    public void setItemTitle(String itemTitle) {
        this.itemTitle = itemTitle;
    }

    public String getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(String itemPrice) {
        this.itemPrice = itemPrice;
    }

    public String getItemRate() {
        return itemRate;
    }

    public void setItemRate(String itemRate) {
        this.itemRate = itemRate;
    }

    public String getItemImg() {
        return itemImg;
    }

    public void setItemImg(String itemImg) {
        this.itemImg = itemImg;
    }
}
