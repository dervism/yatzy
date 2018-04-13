package no.nav.model.selection;

import no.nav.model.Category;
import no.nav.model.ScoreCard;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class EqualSelectionTest {

    private final ScoreCard empty = new ScoreCard();

    @Test
    void should_select_the_highest_valued_pair() {
        EqualSelection strategy = new EqualSelection(SelectionParams.of(Arrays.asList(5, 6, 1, 5, 1), empty));

        Optional<Category> category = strategy.select();
        assertTrue(category.isPresent());

        System.out.println(category);
        assertSame(category.get(), Category.FIVE);
    }

    @Test
    void should_select_the_next_highest_valued_pair_if_taken() {
        EqualSelection strategy = new EqualSelection(SelectionParams.of(Arrays.asList(5, 6, 1, 5, 1), ScoreCard.of(Collections.singletonList(Category.FIVE))));

        Optional<Category> category = strategy.select();
        assertTrue(category.isPresent());

        System.out.println(category);
        assertSame(category.get(), Category.ONE);
    }

    @Test
    void should_select_a_pair_of_one() {
        EqualSelection strategy = new EqualSelection(SelectionParams.of(Arrays.asList(1, 1, 4, 5, 6), empty));

        Optional<Category> category = strategy.select();
        assertTrue(category.isPresent());

        System.out.println(category);
        assertSame(category.get(), Category.ONE);
    }

    @Test
    void should_select_the_highest_valued_dice_when_no_pair_is_present() {
        EqualSelection strategy = new EqualSelection(SelectionParams.of(Arrays.asList(6, 4, 3, 2, 1), empty));

        Optional<Category> category = strategy.orElse(new MaxValueSelection());
        assertTrue(category.isPresent());

        System.out.println(category);
        assertSame(category.get(), Category.SIX);
    }

    @Test
    void should_select_the_lowest_valued_dice_when_no_pair_is_present() {
        EqualSelection strategy = new EqualSelection(SelectionParams.of(Arrays.asList(6, 4, 3, 2, 1), empty));

        Optional<Category> category = strategy.orElse(new MinValueSelection());
        assertTrue(category.isPresent());

        System.out.println(category);
        assertSame(category.get(), Category.ONE);
    }

    @Test
    public void should_return_empty_selection_if_no_equals() {
        EqualSelection strategy = new EqualSelection(SelectionParams.of(Arrays.asList(1, 2, 4, 5, 6), empty));

        Optional<Category> category = strategy.select();

        assertFalse(category.isPresent());
    }

    @Test
    public void can_be_combined_with_And_has_full_house() {
        EqualSelection selection = new EqualSelection(SelectionParams.of(Arrays.asList(1, 1, 4, 4, 4), empty));
        System.out.println("selection: " + selection.select());

        EqualSelection anotherSelection = new EqualSelection(3);

        assertTrue(selection.and(anotherSelection));
    }

    @Test
    public void can_be_combined_with_And_has_no_full_house() {
        EqualSelection selection = new EqualSelection(SelectionParams.of(Arrays.asList(1, 1, 4, 4, 5), empty));
        System.out.println("selection: " + selection.select());

        EqualSelection anotherSelection = new EqualSelection(3);

        assertFalse(selection.and(anotherSelection));
    }

    @Test
    public void can_be_combined_with_Or() {
        EqualSelection selection = new EqualSelection(SelectionParams.of(Arrays.asList(1, 3, 4, 4, 4), empty));
        System.out.println("selection: " + selection.select());

        EqualSelection anotherSelection = new EqualSelection(3);

        assertTrue(selection.or(anotherSelection));
    }
}