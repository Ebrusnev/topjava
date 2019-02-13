package ru.javawebinar.topjava.model;

import java.time.LocalDateTime;
import java.util.concurrent.atomic.AtomicInteger;

public class MealTo {

    private AtomicInteger id = new AtomicInteger(0);

    private final LocalDateTime dateTime;

    private final String description;

    private final int calories;

    private final boolean excess;

    public MealTo(int id, LocalDateTime dateTime, String description, int calories, boolean excess) {
        setId(id);
        this.dateTime = dateTime;
        this.description = description;
        this.calories = calories;
        this.excess = excess;
    }

    public int getId() {
        return id.get();
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public String getDescription() {
        return description;
    }

    public int getCalories() {
        return calories;
    }

    public boolean isExcess() {
        return excess;
    }

    @Override
    public String toString() {
        return "MealTo{" +
                "dateTime=" + dateTime +
                ", description='" + description + '\'' +
                ", calories=" + calories +
                ", excess=" + excess +
                '}';
    }
}