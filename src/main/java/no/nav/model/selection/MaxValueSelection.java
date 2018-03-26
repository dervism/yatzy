package no.nav.model.selection;

import no.nav.Util;
import no.nav.model.Category;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public class MaxValueSelection implements Selection {

    @Override
    public Optional<Category> select(Collection<Integer> list, Collection<Category> taken) {
        Integer max = Util.max(list);

        Category category = Category.fromIndex(max);
        if (!taken.contains(category)) return Optional.of(category);

        List<Integer> notTaken = Util.availableCategories(list, taken);

        // the currently available categories is not among the dice we got
        if (notTaken.isEmpty()) return Optional.empty();

        Integer nextMax = Util.max(notTaken);
        return Optional.of(Category.fromIndex(nextMax));
    }
}
