package ru.javawebinar.topjava.dao;

import ru.javawebinar.topjava.mock.MockedData;
import ru.javawebinar.topjava.model.Meal;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class MealDaoImpl implements MealDao {

    private List<Meal> meals;

    private AtomicInteger id = new AtomicInteger(0);

    public MealDaoImpl() {
        this.meals = MockedData.createMealsList(id);
    }

    @Override
    public Meal add(Meal meal) {
        meal.setId(id.getAndIncrement());
        return meal;
    }

    @Override
    public Meal update(Meal meal) {
        Meal mealToUpdate = get(meal.getId());
        mealToUpdate.setDateTime(meal.getDateTime());
        mealToUpdate.setDescription(meal.getDescription());
        mealToUpdate.setCalories(meal.getCalories());

        return null;
    }

    @Override
    public Meal delete(int id) {
        meals.remove(get(id));
        return null;
    }

    @Override
    public Meal get(int id) {
        return meals.stream().filter(meal -> meal.getId() == id).findFirst().orElse(null);
    }

    @Override
    public List<Meal> getAll() {
        return meals;
    }
}
