package no.nav.strategy;

import no.nav.model.Category;
import no.nav.model.ScoreCard;
import no.nav.model.selection.RandomSelection;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Optional;

import static no.nav.model.Category.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

class RandomSelectionTest {

    @Test
    void select() {
        RandomSelection r = new RandomSelection(Arrays.asList(1, 2, 3, 4, 5), new ScoreCard());
        Optional<Category> category = r.select();

        assertTrue(category.isPresent());
    }

    @Test
    void select_only_not_taken() {
        RandomSelection r = new RandomSelection(Arrays.asList(1, 2, 3, 4, 5), ScoreCard.of(ONE, TWO, THREE, FOUR, SIX));
        Optional<Category> category = r.select();

        assertTrue(category.isPresent());
        assertTrue(category.get() == FIVE);
    }
}