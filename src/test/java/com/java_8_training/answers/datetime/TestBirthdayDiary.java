package com.java_8_training.answers.datetime;

import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.time.Month;
import java.util.Set;

import static org.junit.Assert.*;

public class TestBirthdayDiary
{

    private BirthdayDiary birthdayDiary;

    @Before
    public void before() {
        birthdayDiary = new BirthdayDiary();
    }

    @Test
    public void create_a_birthday_for_henry_viii() {
        String expectedName = "Henry VIII";
        LocalDate birthday = birthdayDiary.addBirthday(expectedName, 28, 6, 1941);
        assertEquals(1941, birthday.getYear());
        assertEquals(Month.JUNE, birthday.getMonth());
        assertEquals(28, birthday.getDayOfMonth());
    }

    @Test
    public void can_retrieve_birthday_for_henry_viii() {
        String henry = "Henry VIII";
        birthdayDiary.addBirthday(henry, 28, 6, 1941);
        LocalDate expectedDate = LocalDate.of(1941, Month.JUNE, 28);
        LocalDate birthday = birthdayDiary.getBirthdayFor(henry);
        assertEquals(expectedDate, birthday);
    }

    @Test
    public void jim_is_30_in_2014() {
        String jim = "Jim";
        birthdayDiary.addBirthday(jim, 29, 9, 1984);
        int age = birthdayDiary.getAgeInYear(jim, 2014);
        assertEquals(30, age);
    }

    @Test
    public void find_people_who_are_30_in_2014() {
        birthdayDiary.addBirthday("Jim", 29, 9, 1984);
        birthdayDiary.addBirthday("Mark", 1, 1, 1984);
        birthdayDiary.addBirthday("Old John", 1, 1, 1930);
        Set<String> thirty = birthdayDiary.getFriendsOfAgeIn(30, 2014);
        assertTrue(thirty.contains("Jim"));
        assertTrue(thirty.contains("Mark"));
        assertFalse(thirty.contains("Old John"));
    }

    @Test
    public void total_ages_of_contacts() {
        LocalDate twentyFive = LocalDate.now().minusYears(25);
        LocalDate thirty = LocalDate.now().minusYears(30);
        LocalDate ten = LocalDate.now().minusYears(10);
        birthdayDiary.addBirthday("Jim", thirty.getDayOfMonth(), thirty.getMonthValue(), thirty.getYear());
        birthdayDiary.addBirthday("Maggie", twentyFive.getDayOfMonth(), twentyFive.getMonthValue(), twentyFive.getYear());
        birthdayDiary.addBirthday("Trish", ten.getDayOfMonth(), ten.getMonthValue(), ten.getYear());

        int total = birthdayDiary.getTotalAgeInYears();
        assertEquals(65, total);
    }

    @Test
    public void total_age_zero_when_no_contacts() {
        assertEquals(0, birthdayDiary.getTotalAgeInYears());
    }
}
