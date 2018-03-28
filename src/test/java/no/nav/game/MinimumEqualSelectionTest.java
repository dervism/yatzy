package no.nav.game;

import no.nav.model.Category;
import no.nav.model.selection.MaxValueSelection;
import no.nav.model.selection.MinValueSelection;
import no.nav.model.selection.MinimumEqualSelection;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MinimumEqualSelectionTest {

    private final List<Category> empty = Collections.emptyList();

    @Test
    void should_select_the_highest_valued_pair() {
        MinimumEqualSelection strategy = new MinimumEqualSelection(Arrays.asList(5, 6, 1, 5, 1), empty);

        Optional<Category> category = strategy.select();
        assertTrue(category.isPresent());

        System.out.println(category);
        assertTrue(category.get() == Category.FIVE);
    }

    @Test
    void should_select_the_next_highest_valued_pair_if_taken() {
        MinimumEqualSelection strategy = new MinimumEqualSelection(Arrays.asList(5, 6, 1, 5, 1), Arrays.asList(Category.FIVE));

        Optional<Category> category = strategy.select();
        assertTrue(category.isPresent());

        System.out.println(category);
        assertTrue(category.get() == Category.ONE);
    }

    @Test
    void should_select_a_pair_of_one() {
        MinimumEqualSelection strategy = new MinimumEqualSelection(Arrays.asList(1, 1, 4, 5, 6), empty);

        Optional<Category> category = strategy.select();
        assertTrue(category.isPresent());

        System.out.println(category);
        assertTrue(category.get() == Category.ONE);
    }

    @Test
    void should_select_the_highest_valued_dice_when_no_pair_is_present() {
        MinimumEqualSelection strategy = new MinimumEqualSelection(Arrays.asList(6, 4, 3, 2, 1), empty);

        Optional<Category> category = strategy.orElse(new MaxValueSelection());
        assertTrue(category.isPresent());

        System.out.println(category);
        assertTrue(category.get() == Category.SIX);
    }

    @Test
    void should_select_the_lowest_valued_dice_when_no_pair_is_present() {
        MinimumEqualSelection strategy = new MinimumEqualSelection(Arrays.asList(6, 4, 3, 2, 1), empty);

        Optional<Category> category = strategy.orElse(new MinValueSelection());
        assertTrue(category.isPresent());

        System.out.println(category);
        assertTrue(category.get() == Category.ONE);
    }

    @Test
    public void should_return_empty_selection_if_no_equals() {
        MinimumEqualSelection strategy = new MinimumEqualSelection(Arrays.asList(1, 2, 4, 5, 6), empty);

        Optional<Category> category = strategy.select();

        assertFalse(category.isPresent());
    }
}