package com.no_18002402.healthapp;

public class DiaryEntries {

    String Food, Date;

    public DiaryEntries() {
    }

    public String getFood() {
        return Food;
    }

    public void setFood(String food) {
        Food = food;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;

    }
    public String toString()
    {
        return Food + "" + Date;
    }

}
