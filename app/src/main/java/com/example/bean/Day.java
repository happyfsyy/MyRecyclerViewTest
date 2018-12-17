package com.example.bean;

public class Day {
    public static final int NORMAL_TYPE=1;
    public static final int HEADER_TYPE=2;
    private int imgId;
    private String name;
    private String headerText;
    private int type;

    public Day(int imgId,String name,int type){
        this.imgId=imgId;
        this.name=name;
        this.type=type;
    }
    public Day(String headerText,int type){
        this.headerText=headerText;
        this.type=type;
    }
    public  int getImgId(){
        return imgId;
    }
    public String getName(){
        return name;
    }
    public String getHeaderText(){
        return headerText;
    }
    public int getType(){
        return type;
    }
}
