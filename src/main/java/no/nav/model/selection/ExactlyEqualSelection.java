package no.nav.model.selection;

import no.nav.model.Category;
import no.nav.model.ScoreSheet;

import java.util.Collection;
import java.util.Comparator;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ExactlyEqualSelection extends AbstractSelection {

    private int minimum;

    public ExactlyEqualSelection() {
        this(2);
    }

    public ExactlyEqualSelection(int n) {
        super();
        this.minimum = n;
    }

    public ExactlyEqualSelection(Collection<Integer> diceList, ScoreSheet scoreSheet) {
        super(diceList, scoreSheet);
        this.minimum = 2;
    }

    public ExactlyEqualSelection(Collection<Integer> diceList, ScoreSheet scoreSheet, int minimum) {
        super(diceList, scoreSheet);
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
                .filter(entry -> entry.getValue() == minimum)
                .filter(entry -> !getCategoryList().contains(Category.fromIndex(entry.getKey())))
                .max(Comparator.comparing(Map.Entry<Integer, Long>::getValue)
                        .thenComparing(Map.Entry::getKey));

        return max.map(entry -> Category.fromIndex(entry.getKey()));
    }

    @Override
    public Selection build(Collection<Integer> diceList, ScoreSheet scoreSheet) {
        return new ExactlyEqualSelection(diceList, scoreSheet, 2);
    }
}
