package no.nav.model.selection;

import no.nav.model.Category;

import java.util.Collection;
import java.util.Optional;

public class EmptySelection extends AbstractSelection {
    public EmptySelection() {
    }

    public EmptySelection(Collection<Integer> diceList, Collection<Category> categoryList) {
        super(diceList, categoryList);
    }

    @Override
    public Optional<Category> select() {
        return Optional.empty();
    }

    @Override
    public Selection build(Collection<Integer> list, Collection<Category> categories) {
        return new EmptySelection(list, categories);
    }
}
