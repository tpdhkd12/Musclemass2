package com.example.musclemass;

public class Diet_item {

    private String foodname;


    private String carbohydrate;

    private String protein;

    private String fat;

    public Diet_item(String foodname, String carbohydrate,  String protein, String fat) {

        this.carbohydrate = carbohydrate;

        this.foodname = foodname;

        this.protein = protein;

        this.fat = fat;

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
