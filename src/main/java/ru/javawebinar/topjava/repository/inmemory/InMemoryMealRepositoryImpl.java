package ru.javawebinar.topjava.repository.inmemory;

import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.DateTimeUtil;
import ru.javawebinar.topjava.util.MealsUtil;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Repository
public class InMemoryMealRepositoryImpl implements MealRepository {
    private Map<Integer, Map<Integer, Meal>> repository = new ConcurrentHashMap<>();
    private AtomicInteger counter = new AtomicInteger(0);

    {
        for (Meal m : MealsUtil.MEALS) {
            save(m, 1);
        }
    }

    @Override
    public Meal save(Meal meal, int userId) {
        if (meal.isNew()) {
            meal.setId(counter.incrementAndGet());
        }
        if (!repository.containsKey(userId)) {
            repository.put(userId, new HashMap<>());
        }
        repository.get(userId).put(meal.getId(), meal);
        return meal;
    }

    @Override
    public boolean delete(int id, int userId) {
        if (!repository.containsKey(userId) || !repository.get(userId).containsKey(id)) {
            return false;
        }
        repository.get(userId).remove(id);
        return true;
    }

    @Override
    public Meal get(int id, int userId) {
        return repository.get(userId).get(id);
    }

    @Override
    public List<Meal> getAll(int userId, LocalDate startDate, LocalDate endDate, LocalTime startTime, LocalTime endTime) {

        if (!repository.containsKey(userId)) {
            return Collections.emptyList();
        }

        return repository.get(userId).values().stream().filter(meal ->
                DateTimeUtil.isBetweenDate(meal.getDate(), startDate == null ? LocalDate.MIN : startDate,
                        endDate == null ? LocalDate.MAX : endDate) &&
                        DateTimeUtil.isBetweenDate(meal.getTime(), startTime == null ? LocalTime.MIN : startTime,
                                endTime == null ? LocalTime.MAX : endTime))
                .sorted((meal1, meal2) -> meal2.getDateTime().compareTo(meal1.getDateTime())).collect(Collectors.toList());
    }

    @Override
    public List<Meal> getAll(int userId) {
        return getAll(userId, null, null, null, null);
    }

}

