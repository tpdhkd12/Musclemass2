package com.example.musclemass;

import com.prolificinteractive.materialcalendarview.CalendarDay;

public class Diet_item {

    private String foodname;

    private String carbohydrate;

    private String protein;

    private String fat;

    private CalendarDay day;

    public CalendarDay getDay() {
        return day;
    }

    public void setDay(CalendarDay day) {
        this.day = day;
    }

    private String id;

    private String timeStamp;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public Diet_item(String foodname, String carbohydrate, String protein, String fat, CalendarDay day, String id, String timeStamp) {

        this.carbohydrate = carbohydrate;

        this.foodname = foodname;

        this.protein = protein;

        this.fat = fat;

        this.day = day;

        this.id = id;

        this.timeStamp = timeStamp;

    }

    public String getFoodname() {
        return foodname;
    }

    public void setFoodname(String foodname) {
        this.foodname = foodname;
    }

    public String getCarbohydrate() {
        return carbohydrate;
    }

    public void setCarbohydrate(String carbohydrate) {
        this.carbohydrate = carbohydrate;
    }


    public String getProtein() {
        return protein;
    }

    public void setProtein(String protein) {
        this.protein = protein;
    }



    public String getFat() {
        return fat;
    }

    public void setFat(String fat) {
        this.fat = fat;
    }

}
