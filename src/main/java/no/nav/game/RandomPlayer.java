package no.nav.game;

import no.nav.model.Category;
import no.nav.model.ThrowState;
import no.nav.model.selection.RandomSelection;

import java.util.Map;
import java.util.Optional;

public class RandomPlayer extends YatzyPlayer {

    public RandomPlayer() {
    }

    public RandomPlayer(long seed, boolean log) {
        super(seed, log);
    }

    @Override
    protected Optional<Category> selectCategory(Map<Category, Integer> scoresheet, ThrowState state) {
        return new RandomSelection(state.getDices(), scoresheet.keySet()).select();
    }
}
