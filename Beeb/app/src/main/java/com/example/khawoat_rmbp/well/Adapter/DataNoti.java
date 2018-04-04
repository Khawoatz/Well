package com.example.khawoat_rmbp.well.Adapter;

import com.google.gson.annotations.SerializedName;

/**
 * Created by jah on 4/2/2018.
 */

public class DataNoti {
    @SerializedName("Review_id")
    private String id;

    public String getId(){
        return id;
    }
    public void setId(String id){
        this.id = id;
    }

    @SerializedName("Name")
    private String nameCustomer;

    public String getNdCustomer(){
        return nameCustomer;
    }
    public  void setNameCustomer(String nameCustomer){
        this.nameCustomer = nameCustomer;
    }

    @SerializedName("Description")
    private String nameDescription;

    public String getNameDescription(){
        return nameDescription;
    }
    private  void  setNameDescription(String nameDescription){
        this.nameDescription = nameDescription;
    }

    @SerializedName("Points")
    private String Points;

    public String getPoints(){
        return Points;
    }
    private  void  setPoints(String Points){
        this.Points = Points;
    }
}
