package no.nav.model.selection;

import no.nav.model.Category;
import no.nav.model.ScoreCard;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;

class SortedSelectionTest {

    private final ScoreCard empty = new ScoreCard();

    @Test
    void should_select_bycount_and_byvalue() {
        SortedSelection strategy = new SortedSelection(Arrays.asList(5, 6, 1, 5, 1), empty);

        Optional<Category> category = strategy.select();
        assertTrue(category.isPresent());

        System.out.println(category);
        assertTrue(category.get() == Category.FIVE);
    }

    @Test
    void should_select_byvalue() {
        SortedSelection strategy = new SortedSelection(Arrays.asList(5, 6, 2, 3, 1), empty);

        Optional<Category> category = strategy.select();
        assertTrue(category.isPresent());

        System.out.println(category);
        assertTrue(category.get() == Category.SIX);
    }

    @Test
    void should_select_bycount() {
        SortedSelection strategy = new SortedSelection(Arrays.asList(3, 3, 2, 2, 2), empty);

        Optional<Category> category = strategy.select();
        assertTrue(category.isPresent());

        System.out.println(category);
        assertTrue(category.get() == Category.TWO);
    }
}