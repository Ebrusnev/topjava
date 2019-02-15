package ru.javawebinar.topjava.mock;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealTo;
import ru.javawebinar.topjava.util.MealsUtil;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class MockedData {

    private

    public static List<Meal> createMealsList(AtomicInteger id) {
        if (id == null) {
            id = new AtomicInteger(0);
        }
        return Arrays.asList(
                new Meal(id.getAndIncrement(), LocalDateTime.of(2019, Month.JANUARY, 30, 10, 0), "Завтрак", 500),
                new Meal(id.getAndIncrement(), LocalDateTime.of(2019, Month.JANUARY, 30, 13, 0), "Обед", 1000),
                new Meal(id.getAndIncrement(), LocalDateTime.of(2019, Month.JANUARY, 30, 20, 0), "Ужин", 500),
                new Meal(id.getAndIncrement(), LocalDateTime.of(2019, Month.JANUARY, 31, 10, 0), "Завтрак", 1000),
                new Meal(id.getAndIncrement(), LocalDateTime.of(2019, Month.JANUARY, 31, 13, 0), "Обед", 500),
                new Meal(id.getAndIncrement(), LocalDateTime.of(2019, Month.JANUARY, 31, 20, 0), "Ужин", 510)
        );
    }

    public static List<MealTo> createMealsToList(List<Meal> meals) {
        return MealsUtil.getFilteredWithExcess(
                meals,
                LocalTime.MIN,
                LocalTime.MAX,
                2000
        );
    }

}
