package com.example.retrofitroom.shared;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class helpers {
    public static String getDayOfWeek(String dayOfWeek){
        switch (dayOfWeek) {
            case "MONDAY":
                dayOfWeek = "Segunda-feira";
                break;
            case "TUESDAY":
                dayOfWeek = "Terça-feira";
                break;
            case "WEDNESDAY":
                dayOfWeek = "Quarta-feira";
                break;
            case "THURSDAY":
                dayOfWeek = "Quinta-feira";
                break;
            case "FRIDAY":
                dayOfWeek = "Sexta-feira";
                break;
            case "SATURDAY":
                dayOfWeek = "Sábado";
                break;
            case "SUNDAY":
                dayOfWeek = "Domingo";
                break;
        }

        return dayOfWeek;
    }

    public static String getDayOfWeekByIndex(int dayOfWeek){
        String dayOfWeekString = null;
        switch (dayOfWeek) {
            case 0:
                dayOfWeekString = "MONDAY";
                break;
            case 1:
                dayOfWeekString = "TUESDAY";
                break;
            case 2:
                dayOfWeekString = "WEDNESDAY";
                break;
            case 3:
                dayOfWeekString = "THURSDAY";
                break;
            case 4:
                dayOfWeekString = "FRIDAY";
                break;
            case 5:
                dayOfWeekString = "SATURDAY";
                break;
            case 6:
                dayOfWeekString = "SUNDAY";
                break;
        }

        return dayOfWeekString;
    }

    public  static ArrayList<String> getDaysOfWeek(){
        ArrayList<String> days = new ArrayList<String>();

        days.add("Segunda-feira");
        days.add("Terça-feira");
        days.add("Quarta-feira");
        days.add("Quinta-feira");
        days.add("Sexta-feira");
        days.add("Sábado");
        days.add("Domingo");

        return days;
    }
}
