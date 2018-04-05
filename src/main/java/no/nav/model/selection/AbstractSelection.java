package no.nav.model.selection;

import no.nav.model.Category;
import no.nav.model.ScoreCard;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;

public abstract class AbstractSelection implements Selection {

    protected java.util.Collection<Integer> diceList;
    protected ScoreCard scoreCard;

    public AbstractSelection() {
        this(Collections.emptyList(), new ScoreCard());
    }

    public AbstractSelection(Collection<Integer> diceList, ScoreCard scoreCard) {
        this.diceList = diceList;
        this.scoreCard = scoreCard;
    }

    public Optional<Category> orElse(Selection... elseStrategies) {
        Optional<Category> selected = select();

        if (!selected.isPresent()) {
            for (Selection selection : elseStrategies) {
                Optional<Category> category = selection.build(getDiceList(), scoreCard).select();
                if (category.isPresent()) return category;
            }
        }

        return selected;
    }

    public boolean and(Selection selection) {
        Selection anotherSelection = selection.build(getDiceList(), scoreCard);
        handleEqualSelection(anotherSelection, selection);
        return select().isPresent() && anotherSelection.select().isPresent();
    }

    public boolean or(Selection anotherStrategy) {
        Selection selection = anotherStrategy.build(getDiceList(), scoreCard);
        handleEqualSelection(anotherStrategy, selection);
        return select().isPresent() || selection.select().isPresent();
    }

    private void handleEqualSelection(Selection anotherSelection, Selection selection) {
        if (anotherSelection instanceof MinimumEqualSelection) {
            ((MinimumEqualSelection)anotherSelection).setMinimum(((MinimumEqualSelection)selection).getMinimum());
        }
        if (anotherSelection instanceof ExactlyEqualSelection) {
            ((ExactlyEqualSelection)anotherSelection).setMinimum(((ExactlyEqualSelection)selection).getMinimum());
        }
    }

    public Collection<Integer> getDiceList() {
        return diceList;
    }

    public Collection<Category> getCategoryList() {
        return scoreCard.selectedCategories();
    }
}
