package no.nav.model.selection;

import no.nav.Util;
import no.nav.model.Category;

import java.util.*;

public class RandomSelection extends AbstractSelection {

    public RandomSelection() {
        super();
    }

    public RandomSelection(Collection<Integer> diceList, Collection<Category> categoryList) {
        super(diceList, categoryList);
    }

    public static Category random(Collection<Category> list) {
        if (list.isEmpty()) throw new IllegalStateException("Cannot play from an empty list.");
        List<Category> categories = new ArrayList<>(list);
        Collections.shuffle(categories);
        return categories.get(0);
    }

    @Override
    public Optional<Category> select() {
        if (getDiceList().isEmpty()) return Optional.empty();

        List<Integer> notTaken = Util.selectableValues(getDiceList(), getCategoryList());

        // the currently available categories is not among the dice we got
        if (notTaken.isEmpty()) return Optional.empty();

        Collections.shuffle(notTaken);
        return Optional.of(Category.fromIndex(notTaken.get(0)));
    }

    @Override
    public Selection build(Collection<Integer> list, Collection<Category> categories) {
        return new RandomSelection(list, categories);
    }
}
