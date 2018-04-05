package no.nav.model.selection;

import no.nav.model.Category;
import no.nav.model.ScoreCard;

import java.util.Collection;
import java.util.Optional;

public interface Selection {

    /**
     * Given a set of dice, select a category.
     *
     * @return A Category enum.
     */
    Optional<Category> select();

    /**
     * Given a set of dice and categories, construct
     * a new Selection-object.
     *
     * @param diceList a collection of dice
     * @param scoreCard a collection of categories
     * @return a new instance of a Selection-object.
     */
    Selection build(Collection<Integer> diceList, ScoreCard scoreCard);
}
