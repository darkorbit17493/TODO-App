package com.example.todoapp.model.utility;

import java.util.Calendar;

public class TimeUtility {
    public static boolean isNotPastDate(int day, int month, int year) {
        //Check for undefined values marked with -1
        if(day <= 0 || month <= 0 || year <= 0) {
            return false;
        }

        // Get the current date
        int currentDay = getCurrentDay();
        int currentMonth = getCurrentMonth();
        int currentYear = getCurrentYear();

        // Validate that the date is in the future
        if(year < currentYear) {
            return false;
        } else if(year == currentYear) {
            if(month < currentMonth) {
                return false;
            } else if(month == currentMonth) {
                if(day < currentDay) {
                    return false;
                }
            }
        }

        return true;
    }

    public static boolean isFutureTime(int hour, int minute, int day, int month, int year) {
        if(!isNotPastDate(day, month, year)) {
            return false;
        }

        //Check for undefined values marked with -1
        if(hour <= 0 || minute <= 0) {
            return false;
        }

        // The hours and minutes are only relevant when the selected day is the current day
        if(isToday(day, month, year)) {
            if(hour < getCurrentHour()) {
                return false;
            } else if(getCurrentHour() == hour) {
                return minute > getCurrentMinute();
            }
        }

        return true;
    }

    public static boolean isToday(int day, int month, int year) {
        return (day == getCurrentDay() && month == getCurrentMonth() && year == getCurrentYear());
    }

    public static int getCurrentMinute() {
        Calendar calendar = Calendar.getInstance();
        return calendar.get(Calendar.MINUTE);
    }

    public static int getCurrentHour() {
        Calendar calendar = Calendar.getInstance();
        return calendar.get(Calendar.HOUR_OF_DAY);
    }

    public static int getCurrentDay() {
        Calendar calendar = Calendar.getInstance();
        return calendar.get(Calendar.DAY_OF_MONTH);
    }

    public static int getCurrentMonth() {
        Calendar calendar = Calendar.getInstance();
        return calendar.get(Calendar.MONTH) + 1; // Java implements the month from 0 to 11
    }

    public static int getCurrentYear() {
        Calendar calendar = Calendar.getInstance();
        return calendar.get(Calendar.YEAR);
    }
}
