package com.example.todoapp.model.utility;

public class Formatter {
    public static String getFormattedDate(int day, int month, int year) {
        String formattedDate = "";

        //If either the day or month is single digit add a 0 to the left of it
        if(day < 10) {
            formattedDate += "0";
        }

        formattedDate += day;
        formattedDate += "/";

        if(month < 10) {
            formattedDate += "0";
        }
        formattedDate += month;
        formattedDate += "/";
        formattedDate += year;

        return formattedDate;
    }

    public static String getFormattedTime(int hour, int minute) {
        String formatedTime = "";

        //If either the hour or minute is single digit add a 0 to the left of it
        if(hour < 10) {
            formatedTime += "0";
        }
        formatedTime += hour;
        formatedTime += ":";

        if(minute < 10) {
            formatedTime += "0";
        }
        formatedTime += minute;

        return formatedTime;
    }
}
