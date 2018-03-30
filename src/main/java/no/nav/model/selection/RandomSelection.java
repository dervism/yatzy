package no.nav.model.selection;

import no.nav.model.Category;
import no.nav.model.ScoreSheet;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class RandomSelection extends AbstractSelection {

    public RandomSelection() {
        super();
    }

    public RandomSelection(Collection<Integer> diceList, ScoreSheet scoreSheet) {
        super(diceList, scoreSheet);
    }

    @Override
    public Optional<Category> select() {
        if (getDiceList().isEmpty()) return Optional.empty();

        List<Integer> notTaken = scoreSheet.selectableValues(getDiceList());

        // the currently available categories is not among the dice we got
        if (notTaken.isEmpty()) return Optional.empty();

        Collections.shuffle(notTaken);
        return Optional.of(Category.fromIndex(notTaken.get(0)));
    }

    @Override
    public Selection build(Collection<Integer> diceList, ScoreSheet scoreSheet) {
        return new RandomSelection(diceList, scoreSheet);
    }
}
