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

    public Collection<Integer> getDiceList() {
        return diceList;
    }

    public Collection<Category> getCategoryList() {
        return categoryList;
    }
}
