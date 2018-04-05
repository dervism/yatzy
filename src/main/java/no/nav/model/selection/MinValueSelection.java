package no.nav.model.selection;

import no.nav.Util;
import no.nav.model.Category;
import no.nav.model.ScoreCard;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public class MinValueSelection extends AbstractSelection {

    public MinValueSelection() {
        super();
    }

    public MinValueSelection(Collection<Integer> diceList, ScoreCard scoreCard) {
        super(diceList, scoreCard);
    }

    @Override
    public Optional<Category> select() {
        Integer max = Util.min(getDiceList());

        Category category = Category.fromIndex(max);
        if (!getCategoryList().contains(category)) return Optional.of(category);

        List<Integer> notTaken = scoreCard.selectableValues(getDiceList());

        // the currently available categories is not among the dice we got
        if (notTaken.isEmpty()) return Optional.empty();

        Integer nextMax = Util.min(notTaken);
        return Optional.of(Category.fromIndex(nextMax));
    }

    @Override
    public Selection build(Collection<Integer> diceList, ScoreCard scoreCard) {
        return new MinValueSelection(diceList, scoreCard);
    }
}
