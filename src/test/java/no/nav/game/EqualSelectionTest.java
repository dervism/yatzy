package no.nav.game;

import no.nav.model.Category;
import no.nav.model.selection.EqualSelection;
import no.nav.model.selection.MaxValueSelection;
import no.nav.model.selection.MinValueSelection;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class EqualSelectionTest {

    @Test
    void should_select_the_highest_valued_pair() {
        EqualSelection strategy = new EqualSelection();

        Optional<Category> category = strategy.select(Arrays.asList(5, 6, 1, 5, 1));
        assertTrue(category.isPresent());

        System.out.println(category);
        assertTrue(category.get() == Category.FIVE);
    }

    @Test
    void should_select_the_next_highest_valued_pair_if_taken() {
        EqualSelection strategy = new EqualSelection();

        Optional<Category> category = strategy.select(Arrays.asList(5, 6, 1, 5, 1), Arrays.asList(Category.FIVE));
        assertTrue(category.isPresent());

        System.out.println(category);
        assertTrue(category.get() == Category.ONE);
    }

    @Test
    void should_select_a_pair_of_one() {
        EqualSelection strategy = new EqualSelection();

        Optional<Category> category = strategy.select(Arrays.asList(1, 1, 4, 5, 6));
        assertTrue(category.isPresent());

        System.out.println(category);
        assertTrue(category.get() == Category.ONE);
    }

    @Test
    void should_select_the_highest_valued_dice_when_no_pair_is_present() {
        EqualSelection strategy = new EqualSelection();

        Optional<Category> category = strategy.orElse(Arrays.asList(6, 4, 3, 2, 1), new MaxValueSelection());
        assertTrue(category.isPresent());

        System.out.println(category);
        assertTrue(category.get() == Category.SIX);
    }

    @Test
    void should_select_the_lowest_valued_dice_when_no_pair_is_present() {
        EqualSelection strategy = new EqualSelection();

        Optional<Category> category = strategy.orElse(Arrays.asList(6, 4, 3, 2, 1), new MinValueSelection());
        assertTrue(category.isPresent());

        System.out.println(category);
        assertTrue(category.get() == Category.ONE);
    }

    @Test
    public void should_return_empty_selection_if_no_equals() {
        EqualSelection strategy = new EqualSelection();

        Optional<Category> category = strategy.select(Arrays.asList(1, 2, 4, 5, 6));

        assertFalse(category.isPresent());
    }
}