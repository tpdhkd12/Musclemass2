package com.example.musclemass;

public class Muscle_item {

    private String kg;

    private String count;

    private String set;

    public String getKg() {
        return kg;
    }

    public void setKg(String kg) {
        this.kg = kg;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getSet() {
        return set;
    }

    public void setSet(String set) {
        this.set = set;
    }

    public Muscle_item(String kg, String count, String set) {
        this.kg = kg;
        this.count = count;
        this.set = set;
    }
}
