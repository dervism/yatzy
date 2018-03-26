package no.nav.model.selection;

import no.nav.model.Category;

import java.util.Collection;
import java.util.Comparator;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SortedSelection implements Selection {

    @Override
    public Optional<Category> select(Collection<Integer> list, Collection<Category> taken) {
        Stream<Map.Entry<Integer, Long>> sorted = list.stream()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet()
                .stream()
                .filter(entry -> !taken.contains(Category.fromIndex(entry.getKey())))
                .sorted(Comparator.comparing(Map.Entry<Integer, Long>::getValue)
                        .thenComparing(Comparator.comparingLong(o -> o.getKey() * o.getValue()))
                        .thenComparing(Comparator.comparingLong(Map.Entry::getKey)).reversed());

        Optional<Map.Entry<Integer, Long>> sortedFirst = sorted.findFirst();

        return sortedFirst.map(value -> Category.fromIndex(value.getKey()));
    }

}
