package com.example.khawoat_rmbp.well.Adapter;

import com.google.gson.annotations.SerializedName;

/**
 * Created by jah on 4/6/2018.
 */

public class DataProfile {
    @SerializedName("Masseuse_id")
    private String id;

    public String getId(){
        return id;
    }
    public void setId(String id){
        this.id = id;
    }

    @SerializedName("namemass")
    private String nameMass;

    public String getNameMass(){
        return nameMass;
    }
    public  void setNameMass(String nameMass){
        this.nameMass = nameMass;
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
