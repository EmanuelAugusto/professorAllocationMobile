package com.example.retrofitroom.shared;

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
}
