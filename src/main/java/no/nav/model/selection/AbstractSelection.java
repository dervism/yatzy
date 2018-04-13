package no.nav.model.selection;

import no.nav.model.Category;
import no.nav.model.ScoreCard;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;

public abstract class AbstractSelection implements Selection {

    protected SelectionParams selectionParams;

    public AbstractSelection() {
        this(new SelectionParams(Collections.emptyList(), new ScoreCard()));
    }

    public AbstractSelection(SelectionParams selectionParams) {
        this.selectionParams = selectionParams;
    }

    public Optional<Category> orElse(Selection... elseStrategies) {
        Optional<Category> selected = select();

        if (!selected.isPresent()) {
            for (Selection selection : elseStrategies) {
                Optional<Category> category = selection.build(selectionParams).select();
                if (category.isPresent()) return category;
            }
        }

        return selected;
    }

    public boolean and(Selection selection) {
        Selection anotherSelection = selection.build(selectionParams);
        handleEqualSelection(anotherSelection, selection);
        return select().isPresent() && anotherSelection.select().isPresent();
    }

    public boolean or(Selection anotherStrategy) {
        Selection selection = anotherStrategy.build(selectionParams);
        handleEqualSelection(anotherStrategy, selection);
        return select().isPresent() || selection.select().isPresent();
    }

    private void handleEqualSelection(Selection anotherSelection, Selection selection) {
        if (anotherSelection instanceof EqualSelection) {
            ((EqualSelection)anotherSelection).setMinimum(((EqualSelection)selection).getMinimum());
        }
    }

    public Collection<Integer> getDiceList() {
        return selectionParams.getDiceList();
    }

    public Collection<Category> getCategoryList() {
        return selectionParams.getScoreCard().selectedCategories();
    }
}
