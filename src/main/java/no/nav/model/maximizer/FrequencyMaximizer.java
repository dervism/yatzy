package no.nav.model.maximizer;

import no.nav.model.Category;
import no.nav.model.ThrowState;

import java.util.Collections;
import java.util.Map;

import static no.nav.model.Category.*;

public class FrequencyMaximizer implements Maximizer {
    public FrequencyMaximizer() {
    }

    @Override
    public Category maximize(Category selectedCategory, ThrowState state, Map<Category, Integer> scoresheet) {
        if (selectedCategory == Category.SIX) {
            int freq6 = Collections.frequency(state.getDices(), 6);
            if (freq6 < 3) {
                return !scoresheet.containsKey(ONE) ? ONE : !scoresheet.containsKey(TWO) ? TWO : !scoresheet.containsKey(THREE) ? THREE : selectedCategory;
            }
        }

        if (selectedCategory == Category.FIVE) {
            int freq5 = Collections.frequency(state.getDices(), 5);
            if (freq5 < 2) {
                return !scoresheet.keySet().contains(ONE) ? ONE : !scoresheet.keySet().contains(TWO) ? TWO : !scoresheet.containsKey(THREE) ? THREE : selectedCategory;
            }
        }

        if (selectedCategory == Category.FOUR) {
            int freq4 = Collections.frequency(state.getDices(), 4);
            if (freq4 < 2) {
                return !scoresheet.keySet().contains(ONE) ? ONE : !scoresheet.keySet().contains(TWO) ? TWO : !scoresheet.containsKey(THREE) ? THREE : selectedCategory;
            }
        }

        return selectedCategory;
    }
}
