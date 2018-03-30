package no.nav.model.selection;

import no.nav.Util;
import no.nav.model.Category;
import no.nav.model.ScoreSheet;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public class MaxValueSelection extends AbstractSelection {

    public MaxValueSelection() {
        super();
    }

    public MaxValueSelection(Collection<Integer> diceList, ScoreSheet scoreSheet) {
        super(diceList, scoreSheet);
    }

    @Override
    public Optional<Category> select() {
        Integer max = Util.max(getDiceList());

        Category category = Category.fromIndex(max);
        if (!getCategoryList().contains(category)) return Optional.of(category);

        List<Integer> notTaken = scoreSheet.selectableValues(getDiceList());

        // the currently available categories is not among the dice we got
        if (notTaken.isEmpty()) return Optional.empty();

        Integer nextMax = Util.max(notTaken);
        return Optional.of(Category.fromIndex(nextMax));
    }

    @Override
    public Selection build(Collection<Integer> diceList, ScoreSheet scoreSheet) {
        return new MaxValueSelection(diceList, scoreSheet);
    }
}
