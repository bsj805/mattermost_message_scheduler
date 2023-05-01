package com.mattermost_plugin.message_scheduler.model;

public record TimeRecord(String year, String month, String day, String hour, String minute) {

    public TimeRecord(String year, String month, String day, String hour, String minute) {
        this.year = validateYear(year);
        this.month = validateMonth(month);
        this.day = validateDay(day);
        this.hour = validateHour(hour);
        this.minute = validateMinute(minute);
    }

    private Integer validateLengthReturnInteger(int availableLength1, int availableLength2, String value) {
        if (value.length() != availableLength1 && value.length() != availableLength2) {
            throw new IllegalArgumentException("wrong input: " + value);
        }
        try {
            return Integer.parseInt(value);

        } catch (Exception e) {
            throw new IllegalArgumentException("wrong input: " + value + e);
        }

    }

    private String validateYear(String year) {
        Integer intYear = validateLengthReturnInteger(2, 4, year); // parse Int에서 에러나는지 확인

        if (year.length() == 2) {
            return "20" + year;
        } else {
            return year;
        }
    }

    private String validateMonth(String month) {
        Integer intMonth = validateLengthReturnInteger(1, 2, month);
        if (intMonth >= 1 && intMonth <= 12) {
            return month;
        }
        throw new IllegalArgumentException("wrong month: " + month);
    }

    private String validateDay(String day) {
        Integer intDay = validateLengthReturnInteger(1, 2, day);
        if (intDay >= 1 && intDay <= 31) {
            return day;
        }
        throw new IllegalArgumentException("wrong day: " + day);
    }

    private String validateHour(String hour) {
        Integer intHour = validateLengthReturnInteger(1, 2, hour);
        if (intHour >= 0 && intHour <= 24) {
            return hour;
        }
        throw new IllegalArgumentException("wrong hour: " + hour);
    }

    private String validateMinute(String minute) {
        Integer intMinute = validateLengthReturnInteger(1, 2, minute);
        if (intMinute >= 0 && intMinute <= 60) {
            return minute;
        }
        throw new IllegalArgumentException("wrong minute: " + minute);
    }

}
