package com.example.khawoat_rmbp.well.Adapter;

import com.google.gson.annotations.SerializedName;

/**
 * Created by jah on 3/28/2018.
 */

public class DataReview {
    @SerializedName("Review_id")
    private String id;

    public String getId(){
        return id;
    }
    public void setId(String id){
        this.id = id;
    }

    @SerializedName("Description")
    private String Description;

    public String getDescription(){
        return Description;
    }
    public  void setDescription(String DescriptionMass){
        this.Description = DescriptionMass;
    }

    @SerializedName("Points")
    private String Points;

    public String getPoints(){
        return Points;
    }
    public  void setPoints(String PointsMass){
        this.Points = PointsMass;
    }

}

