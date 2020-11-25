package com.no_18002402.healthapp;

public class DisplayEntries {
    String Weight, Date;

    public DisplayEntries() {

    }

    public String getWeight() {
        return Weight;
    }

    public void setWeight(String weight) {
        Weight = weight;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String toString()
    {
        return Weight + "" +  Date;
    }

}
