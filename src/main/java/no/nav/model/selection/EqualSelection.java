package no.nav.model.selection;

import no.nav.model.Category;

import java.util.Collection;
import java.util.Comparator;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

public class EqualSelection extends AbstractSelection {

    private int minimum;

    public EqualSelection() {
        super();
        this.minimum = 2;
    }

    public EqualSelection(Collection<Integer> diceList, Collection<Category> categoryList) {
        super(diceList, categoryList);
        this.minimum = 2;
    }

    public EqualSelection(Collection<Integer> diceList, Collection<Category> categoryList, int minimum) {
        super(diceList, categoryList);
        this.minimum = minimum;
    }

    public void setMinimum(int minimum) {
        this.minimum = minimum;
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
    public Selection build(Collection<Integer> list, Collection<Category> categories) {
        return new EqualSelection(list, categories, 2);
    }
}
