package no.nav.model.selection;

import no.nav.model.Category;

import java.util.Comparator;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class EqualSelection extends AbstractSelection {

    private int minimum;

    public EqualSelection() {
        this(2);
    }

    public EqualSelection(int n) {
        super();
        this.minimum = n;
    }

    public EqualSelection(SelectionParams selectionParams) {
        super(selectionParams);
        this.minimum = 2;
    }

    public EqualSelection(SelectionParams selectionParams, int minimum) {
        super(selectionParams);
        this.minimum = minimum;
    }

    public void setMinimum(int minimum) {
        this.minimum = minimum;
    }

    public int getMinimum() {
        return minimum;
    }

    @Override
    public Optional<Category> select() {
        Optional<Map.Entry<Integer, Long>> max = getDiceList().stream()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet()
                .stream()
                .filter(exactMatchPredicate(minimum))
                .filter(entry -> !getCategoryList().contains(Category.fromIndex(entry.getKey())))
                .max(Comparator.comparing(Map.Entry<Integer, Long>::getValue)
                        .thenComparing(Map.Entry::getKey));

        return max.map(entry -> Category.fromIndex(entry.getKey()));
    }

    @Override
    public Selection build(SelectionParams selectionParams) {
        return new EqualSelection(selectionParams, 2);
    }

    public static Predicate<Map.Entry<Integer, Long>> exactMatchPredicate(int match) {
        return entry -> entry.getValue() == match;
    }

    public static Predicate<Map.Entry<Integer, Long>> minimumMatchPredicate(int match) {
        return entry -> entry.getValue() >= match;
    }
}
