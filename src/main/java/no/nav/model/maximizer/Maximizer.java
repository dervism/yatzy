package no.nav.model.maximizer;

import no.nav.model.Category;
import no.nav.model.ThrowState;

import java.util.Map;

public interface Maximizer {
    Category maximize(Category selectedCategory, ThrowState state, Map<Category, Integer> scoresheet);
}
