package no.nav.model.selection;

import no.nav.model.Category;
import no.nav.model.ScoreCard;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class RandomSelection extends AbstractSelection {

    public RandomSelection() {
        super();
    }

    public RandomSelection(Collection<Integer> diceList, ScoreCard scoreCard) {
        super(diceList, scoreCard);
    }

    @Override
    public Optional<Category> select() {
        if (getDiceList().isEmpty()) return Optional.empty();

        List<Integer> notTaken = scoreCard.selectableValues(getDiceList());

        // the currently available categories is not among the dice we got
        if (notTaken.isEmpty()) return Optional.empty();

        Collections.shuffle(notTaken);
        return Optional.of(Category.fromIndex(notTaken.get(0)));
    }

    @Override
    public Selection build(Collection<Integer> diceList, ScoreCard scoreCard) {
        return new RandomSelection(diceList, scoreCard);
    }
}
