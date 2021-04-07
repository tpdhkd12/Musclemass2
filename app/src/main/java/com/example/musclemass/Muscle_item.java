package com.example.musclemass;

import com.prolificinteractive.materialcalendarview.CalendarDay;

public class Muscle_item {

    private String kg;

    private String count;

    private String set;

    private CalendarDay day;

    private String id;

    private String timeStamp;

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }


    public CalendarDay getDay() {
        return day;
    }

    public void setDay(CalendarDay day) {
        this.day = day;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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

    public Muscle_item(String kg, String count, String set, CalendarDay day, String id, String timeStamp) {
        this.kg = kg;
        this.count = count;
        this.set = set;
        this.day = day;
        this.id = id;
        this.timeStamp = timeStamp;
    }
}
