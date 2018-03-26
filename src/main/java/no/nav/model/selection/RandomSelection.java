package no.nav.model.selection;

import no.nav.Util;
import no.nav.model.Category;

import java.util.*;

public class RandomSelection implements Selection {

    @Override
    public Optional<Category> select(Collection<Integer> list, Collection<Category> taken) {
        if (list.isEmpty()) return Optional.empty();

        List<Integer> notTaken = Util.availableCategories(list, taken);

        // the currently available categories is not among the dice we got
        if (notTaken.isEmpty()) return Optional.empty();

        Collections.shuffle(notTaken);
        return Optional.of(Category.fromIndex(notTaken.get(0)));
    }

    public static Category random(Collection<Category> list) {
        if (list.isEmpty()) throw new IllegalStateException("Cannot play from an empty list.");
        List<Category> categories = new ArrayList<>(list);
        Collections.shuffle(categories);
        return categories.get(0);
    }

}
