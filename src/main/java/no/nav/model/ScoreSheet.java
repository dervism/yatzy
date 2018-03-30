package no.nav.model;

import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class ScoreSheet implements Supplier<ScoreSheet> {

    private Map<Category, Integer> scoresheet;

    public ScoreSheet() {
        this.scoresheet = new HashMap<>(6);
    }

    public ScoreSheet(Collection<Category> categories) {
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
                .sorted(Comparator.comparing(Enum::ordinal))
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
    public ScoreSheet get() {
        return new ScoreSheet();
    }

    public static ScoreSheet of(Collection<Category> categories) {
        return new ScoreSheet(categories);
    }

    public static ScoreSheet of(Category... categories) {
        return new ScoreSheet(Arrays.stream(categories).collect(Collectors.toList()));
    }

}
