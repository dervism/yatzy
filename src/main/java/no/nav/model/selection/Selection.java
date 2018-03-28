package no.nav.model.selection;

import no.nav.model.Category;

import java.util.Collection;
import java.util.Optional;

public interface Selection {

    Optional<Category> select();

    default Optional<Category> orElse(Selection... elseStrategies) {

        Optional<Category> selected = select();

        if (!selected.isPresent()) {
            for (Selection selection : elseStrategies) {
                Optional<Category> category = selection.build(getDiceList(), getCategoryList()).select();
                if (category.isPresent()) return category;
            }
        }

        return selected;
    }

    Selection build(Collection<Integer> list, Collection<Category> categories);
    Collection<Integer> getDiceList();
    Collection<Category> getCategoryList();
}
