package com.example.khawoat_rmbp.well.Adapter;

import com.google.gson.annotations.SerializedName;

/**
 * Created by jah on 4/5/2018.
 */

public class DataHistoryUser {
    @SerializedName("Res_id")
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


    @SerializedName("TypeName")
    private String nameType;

    public String getNameType(){
        return nameType;
    }
    private  void  setNameType(String nameType){
        this.nameType = nameType;
    }

    @SerializedName("Date")
    private String date;

    public String getDate(){
        return  date;
    }
    public void setDate(String date){
        this.date = date;
    }

    @SerializedName("StartTime")
    private String startTime;

    public String getStartTime(){
        return  startTime;
    }
    public void setStartTime(String startTime){
        this.startTime = startTime;
    }

    @SerializedName("EndTime")
    private String endTime;

    public String getEndTime(){
        return  endTime;
    }
    public void setEndTime(String endTime){
        this.endTime = endTime;
    }

    @SerializedName("Location")
    private String location;

    public String getLocation(){
        return  location;
    }
    public void setLocation(String location){
        this.location = location;
    }


}
