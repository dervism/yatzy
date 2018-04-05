package no.nav.model;

import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class ScoreCard implements Supplier<ScoreCard> {

    private Map<Category, Integer> scoresheet;

    public ScoreCard() {
        this.scoresheet = new HashMap<>(6);
    }

    public ScoreCard(Collection<Category> categories) {
        this.scoresheet = new HashMap<>(6);
        categories.forEach(category -> this.scoresheet.put(category, 0));
    }

    public Collection<Category> selectedCategories() {
        return scoresheet.keySet();
    }

    public List<Integer> selectableValues(Collection<Integer> list) {
        return list.stream()
                .distinct()
                .filter(value -> !scoresheet.keySet().contains(Category.fromIndex(value)))
                .collect(Collectors.toList());
    }

    public List<Category> selectableCategories() {
        return Arrays.stream(Category.values())
                .filter(category -> !scoresheet.keySet().contains(category))
                .collect(Collectors.toList());
    }

    public void put(Category category, int sum) {
        this.scoresheet.put(category, sum);
    }

    public Category anyCategory() {
        return selectableCategories().get(0);
    }

    public boolean hasCategory(Category category) {
        return scoresheet.containsKey(category);
    }
    @Override
    public ScoreCard get() {
        return new ScoreCard();
    }

    public static ScoreCard of(Collection<Category> categories) {
        return new ScoreCard(categories);
    }

    public static ScoreCard of(Category... categories) {
        return new ScoreCard(Arrays.stream(categories).collect(Collectors.toList()));
    }

}
