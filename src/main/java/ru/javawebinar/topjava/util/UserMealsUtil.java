package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.model.UserMealWithExceed;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.*;
import java.util.stream.Collectors;

public class UserMealsUtil {
    public static void main(String[] args) {
        List<UserMeal> mealList = Arrays.asList(
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Завтрак", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 13, 0), "Обед", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 20, 0), "Ужин", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 10, 0), "Завтрак", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 13, 0), "Обед", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 20, 0), "Ужин", 510)
        );
        getFilteredWithExceeded(mealList, LocalTime.of(7, 0), LocalTime.of(12, 0), 2000);
//        .toLocalDate();
//        .toLocalTime();
    }

    public static List<UserMealWithExceed> getFilteredWithExceeded(List<UserMeal> mealList, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {
        Map<LocalDateTime, Integer> caloriesSumByDate = mealList.stream()
                .collect(Collectors.groupingBy(UserMeal::getDateTime, Collectors.summingInt(UserMeal::getCalories)));

        return mealList.stream()
                .filter(userMeal -> TimeUtil.isBetween(userMeal.getDateTime().toLocalTime(), startTime, endTime))
                .map(userMeal -> new UserMealWithExceed(userMeal.getDateTime(), userMeal.getDescription(), userMeal.getCalories(), caloriesSumByDate.get(userMeal.getDateTime()) > caloriesPerDay))
                .collect(Collectors.toList());
    }

    public static List<UserMealWithExceed> getFilteredWithExceededWithCycles(List<UserMeal> mealList, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {

        Map<LocalDateTime, Integer> caloriesSumByDate = new HashMap<>();
        for (UserMeal um : mealList) {
            LocalDateTime date = um.getDateTime();
            if (caloriesSumByDate.containsKey(date)) {
                caloriesSumByDate.put(date, caloriesSumByDate.get(date) + um.getCalories());
            } else {
                caloriesSumByDate.put(date, um.getCalories());
            }
        }
        List<UserMealWithExceed> listToReturn = new ArrayList<>();
        for (UserMeal um : mealList) {
            LocalDateTime date = um.getDateTime();
            if (TimeUtil.isBetween(date.toLocalTime(), startTime, endTime)) {
                listToReturn.add(new UserMealWithExceed(date, um.getDescription(), um.getCalories(), caloriesSumByDate.get(date) > caloriesPerDay));
            }
        }

        return listToReturn;
    }
}
