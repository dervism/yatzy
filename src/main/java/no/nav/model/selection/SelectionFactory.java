package no.nav.model.selection;

public class SelectionFactory {

    public Selection build(Selection selectionClass, SelectionParams selectionParams) {
        if (selectionClass instanceof EqualSelection) {
            return new EqualSelection(selectionParams, ((EqualSelection) selectionClass).getMinimum());
        }

        if (selectionClass instanceof MaxValueSelection) {
            return new MaxValueSelection(selectionParams);
        }

        if (selectionClass instanceof MinValueSelection) {
            return new MinValueSelection(selectionParams);
        }

        if (selectionClass instanceof SortedSelection) {
            return new SortedSelection(selectionParams);
        }

        if (selectionClass instanceof RandomSelection) {
            return new RandomSelection(selectionParams);
        }

        throw new IllegalStateException("Unknown class.");
    }

}
