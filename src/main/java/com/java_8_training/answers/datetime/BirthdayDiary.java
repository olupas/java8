package com.java_8_training.answers.datetime;

import java.time.LocalDate;
import java.time.Month;
import java.time.Period;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class BirthdayDiary {

    private Map<String, LocalDate> birthdays;

    public BirthdayDiary() {
        birthdays = new HashMap<>();
    }

    public LocalDate addBirthday(String name, int day, int month, int year) {
        LocalDate birthday = LocalDate.of(year, month, day);
        birthdays.put(name, birthday);
        return birthday;
    }

    public LocalDate getBirthdayFor(String name) {
        return birthdays.get(name);
    }

    public int getAgeInYear(String name, int year) {
        LocalDate birthday = birthdays.get(name);
        return getAgeInYear(year, birthday);
    }

    private int getAgeInYear(int year, LocalDate birthday) {
        Period period = Period.between(birthday, birthday.withYear(year));
        return period.getYears();
    }

    public Set<String> getFriendsOfAgeIn(int age, int year) {
        return birthdays.keySet().stream()
                .filter(p -> getAgeInYear(p, year) == age)
                .collect(Collectors.toSet());
    }

    public Set<String> getBirthdaysIn(Month month) {
        return birthdays
                .entrySet()
                .stream()
                .filter(p -> p.getValue().getMonth() == month)
                .map(Map.Entry::getKey)
                .collect(Collectors.toSet());
    }

    public int getTotalAgeInYears() {
        int year = LocalDate.now().getYear();
        return birthdays
                .entrySet()
                .stream()
                .mapToInt(entry -> getAgeInYear(year, entry.getValue()))
                .sum();
    }
}
