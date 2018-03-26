package no.nav.model.selection;

import no.nav.model.Category;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;

public interface Selection {

    /**
     * Given a set of dices, play which category to play.
     *
     */
    Optional<Category> select(Collection<Integer> list, Collection<Category> categories);

    default Optional<Category> select(Collection<Integer> list) {
        return select(list, Collections.emptyList());
    }

    default Optional<Category> orElse(Collection<Integer> list, Collection<Category> categories, Selection elseStrategy) {
        Optional<Category> category = select(list, categories);
        return category.isPresent() ? category : elseStrategy.select(list, categories);
    }

    default Optional<Category> orElse(Collection<Integer> list, Selection elseStrategy) {
        Optional<Category> category = select(list, Collections.emptyList());
        return category.isPresent() ? category : elseStrategy.select(list, Collections.emptyList());
    }

}
