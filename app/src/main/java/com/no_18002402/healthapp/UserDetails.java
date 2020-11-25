package com.no_18002402.healthapp;

public class UserDetails {

    private String FullName, Age, Gender, Weight, Height, TargetWeight,TargetSteps;

    public String getGender() {
        return Gender;
    }

    public void setGender(String gender) {
        Gender = gender;
    }

    public String getFullName() {
        return FullName;
    }

    public void setFullName(String fullName) {
        FullName = fullName;
    }

    public String getAge() {
        return Age;
    }

    public void setAge(String age) {
        Age = age;
    }

    public String getWeight() {
        return Weight;
    }

    public void setWeight(String weight) {
        Weight = weight;
    }

    public String getHeight() {
        return Height;
    }

    public void setHeight(String height) {
        Height = height;
    }

    public String getTargetWeight() {
        return TargetWeight;
    }

    public void setTargetWeight(String targetWeight) {
        TargetWeight = targetWeight;
    }

    public String getTargetSteps() {
        return TargetSteps;
    }

    public void setTargetSteps(String targetSteps) {
        TargetSteps = targetSteps;
    }

    public String toString()
    {
        return FullName + "" + Age + "" + Gender + "" + Weight + "" +  Height + "" + TargetWeight + "" + TargetSteps;
    }
}
