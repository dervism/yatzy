package no.nav.model.selection;

import no.nav.model.Category;

import java.util.Collection;
import java.util.Comparator;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

public class EqualSelection implements Selection {

    private int minimum;

    public EqualSelection() {
        this.minimum = 2;
    }

    /**
     * Selects the category with the highest number of equal dice.
     * Returns an empty Optional if no equal dice are found.
     */
    @Override
    public Optional<Category> select(Collection<Integer> list, Collection<Category> taken) {

        Optional<Map.Entry<Integer, Long>> max = list.stream()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet()
                .stream()
                .filter(entry -> entry.getValue() >= minimum)
                .filter(entry -> !taken.contains(Category.fromIndex(entry.getKey())))
                .max(Comparator.comparing(Map.Entry<Integer, Long>::getValue).thenComparing(Map.Entry::getKey));

        return max.map(entry -> Category.fromIndex(entry.getKey()));
    }

    public void setMinimum(int minimum) {
        this.minimum = minimum;
    }
}
