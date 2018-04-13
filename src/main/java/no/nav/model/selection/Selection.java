package no.nav.model.selection;

import no.nav.model.Category;

import java.util.Optional;

public interface Selection {

    /**
     * Given a set of dice, select a category.
     *
     * @return A Category enum.
     */
    Optional<Category> select();

}
