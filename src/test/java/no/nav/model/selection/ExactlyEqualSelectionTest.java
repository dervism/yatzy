package no.nav.model.selection;

import no.nav.model.ScoreCard;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ExactlyEqualSelectionTest {

    private final ScoreCard empty = new ScoreCard();

    @Test
    public void can_be_combined_with_And_has_full_house() {
        ExactlyEqualSelection selection = new ExactlyEqualSelection(Arrays.asList(1, 1, 4, 4, 4), empty);
        System.out.println("selection: " + selection.select());

        ExactlyEqualSelection anotherSelection = new ExactlyEqualSelection(3);

        assertTrue(selection.and(anotherSelection));
    }

    @Test
    public void can_be_combined_with_And_has_no_full_house() {
        ExactlyEqualSelection selection = new ExactlyEqualSelection(Arrays.asList(1, 1, 4, 4, 5), empty);
        System.out.println("selection: " + selection.select());

        ExactlyEqualSelection anotherSelection = new ExactlyEqualSelection(3);

        assertFalse(selection.and(anotherSelection));
    }

}