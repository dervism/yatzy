package no.nav.model.selection;

import no.nav.model.Category;
import no.nav.model.ScoreCard;

import java.util.Collection;
import java.util.Comparator;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

public class MinimumEqualSelection extends AbstractSelection {

    private int minimum;

    public MinimumEqualSelection() {
        this(2);
    }

    public MinimumEqualSelection(int n) {
        super();
        this.minimum = n;
    }

    public MinimumEqualSelection(Collection<Integer> diceList, ScoreCard scoreCard) {
        super(diceList, scoreCard);
        this.minimum = 2;
    }

    public MinimumEqualSelection(Collection<Integer> diceList, ScoreCard scoreCard, int minimum) {
        super(diceList, scoreCard);
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
                .filter(entry -> entry.getValue() >= minimum)
                .filter(entry -> !getCategoryList().contains(Category.fromIndex(entry.getKey())))
                .max(Comparator.comparing(Map.Entry<Integer, Long>::getValue)
                        .thenComparing(Map.Entry::getKey));

        return max.map(entry -> Category.fromIndex(entry.getKey()));
    }

    @Override
    public Selection build(Collection<Integer> diceList, ScoreCard scoreCard) {
        return new MinimumEqualSelection(diceList, scoreCard, 2);
    }
}
