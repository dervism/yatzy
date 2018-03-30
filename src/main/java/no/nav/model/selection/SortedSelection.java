package no.nav.model.selection;

import no.nav.model.Category;
import no.nav.model.ScoreSheet;

import java.util.Collection;
import java.util.Comparator;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SortedSelection extends AbstractSelection {

    public SortedSelection() {
        super();
    }

    public SortedSelection(Collection<Integer> diceList, ScoreSheet scoreSheet) {
        super(diceList, scoreSheet);
    }

    @Override
    public Optional<Category> select() {
        Stream<Map.Entry<Integer, Long>> sorted = getDiceList().stream()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet()
                .stream()
                .filter(entry -> !getCategoryList().contains(Category.fromIndex(entry.getKey())))
                .sorted(Comparator.comparing(Map.Entry<Integer, Long>::getValue)
                        .thenComparingLong(o -> o.getKey() * o.getValue())
                        .thenComparingLong(Map.Entry::getKey).reversed());

        Optional<Map.Entry<Integer, Long>> sortedFirst = sorted.findFirst();

        return sortedFirst.map(value -> Category.fromIndex(value.getKey()));
    }

    @Override
    public Selection build(Collection<Integer> diceList, ScoreSheet scoreSheet) {
        return new SortedSelection(diceList, scoreSheet);
    }
}
