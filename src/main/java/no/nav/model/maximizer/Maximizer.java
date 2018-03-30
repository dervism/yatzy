package no.nav.model.maximizer;

import no.nav.model.Category;
import no.nav.model.ScoreSheet;
import no.nav.model.ThrowState;

@FunctionalInterface
public interface Maximizer {
    Category maximize(Category selectedCategory, ThrowState state, ScoreSheet scoresheet);
}
