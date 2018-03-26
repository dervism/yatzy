package no.nav.model.selection;

import no.nav.model.Category;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;

class SortedSelectionTest {

    @Test
    void should_select_bycount_and_byvalue() {
        SortedSelection strategy = new SortedSelection();

        Optional<Category> category = strategy.select(Arrays.asList(5, 6, 1, 5, 1));
        assertTrue(category.isPresent());

        System.out.println(category);
        assertTrue(category.get() == Category.FIVE);
    }

    @Test
    void should_select_byvalue() {
        SortedSelection strategy = new SortedSelection();

        Optional<Category> category = strategy.select(Arrays.asList(5, 6, 2, 3, 1));
        assertTrue(category.isPresent());

        System.out.println(category);
        assertTrue(category.get() == Category.SIX);
    }

    @Test
    void should_select_bycount() {
        SortedSelection strategy = new SortedSelection();

        Optional<Category> category = strategy.select(Arrays.asList(3, 3, 2, 2, 2));
        assertTrue(category.isPresent());

        System.out.println(category);
        assertTrue(category.get() == Category.TWO);
    }
}