package no.nav;

import no.nav.model.Category;

import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Util {

    public static Integer max(Collection<Integer> list) {
        return list.stream().max(Integer::compareTo).orElseThrow(IllegalStateException::new);
    }

    public static Integer min(Collection<Integer> list) {
        return list.stream().min(Integer::compareTo).orElseThrow(IllegalStateException::new);
    }

    public static int sum(Collection<Integer> list) {
        return list.stream().mapToInt(Integer::intValue).sum();
    }

    public static List<Integer> selectableValues(Collection<Integer> list, Collection<Category> taken) {
        return list.stream()
                .distinct()
                .filter(value -> !taken.contains(Category.fromIndex(value)))
                .collect(Collectors.toList());
    }

    public static List<Category> selectableCategories(Collection<Category> taken) {
        return Arrays.stream(Category.values())
                .filter(category -> !taken.contains(category))
                .sorted(Comparator.comparing(Enum::ordinal))
                .collect(Collectors.toList());
    }
}
