package ru.javawebinar.topjava.dao;

import ru.javawebinar.topjava.model.Meal;

import java.util.List;

public interface MealDao {

    Meal add(Meal meal);

    Meal update(Meal meal);

    Meal delete(int id);

    Meal get(int id);

    List<Meal> getAll();

}
