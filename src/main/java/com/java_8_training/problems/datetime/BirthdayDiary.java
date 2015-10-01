package com.java_8_training.problems.datetime;

import java.time.LocalDate;
import java.time.Month;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class BirthdayDiary {

    private Map<String, LocalDate> birthdays;

    public BirthdayDiary() {
        birthdays = new HashMap<>();
    }

    public LocalDate addBirthday(String name, int day, int month, int year) {
        return null;
    }

    public LocalDate getBirthdayFor(String name) {
        return null;
    }

    public int getAgeInYear(String name, int year) {
        return 0;
    }

    public Set<String> getFriendsOfAgeIn(int age, int year) {
        return null;
    }

    public Set<String> getBirthdaysIn(Month month) {
        return null;
    }

    public int getTotalAgeInYears() {
        return 0;
    }
}