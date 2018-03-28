package no.nav.model.selection;

import no.nav.model.Category;

import java.util.Collection;
import java.util.Collections;

public abstract class AbstractSelection implements Selection {

    protected java.util.Collection<Integer> diceList;
    protected Collection<Category> categoryList;

    public AbstractSelection() {
        this(Collections.emptyList(), Collections.emptyList());
    }

    public AbstractSelection(Collection<Integer> diceList, Collection<Category> categoryList) {
        this.diceList = diceList;
        this.categoryList = categoryList;
    }

    public boolean and(Selection selection) {
        Selection anotherSelection = selection.build(getDiceList(), getCategoryList());
        handleEqualSelection(anotherSelection, selection);
        return select().isPresent() && anotherSelection.select().isPresent();
    }

    public boolean or(Selection anotherStrategy) {
        Selection selection = anotherStrategy.build(getDiceList(), getCategoryList());
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
        return categoryList;
    }
}
