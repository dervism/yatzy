package no.nav.game.player;

import no.nav.model.Category;
import no.nav.model.ScoreCard;
import no.nav.model.ThrowState;
import no.nav.model.selection.RandomSelection;
import no.nav.model.selection.SelectionParams;

import java.util.Optional;

public class RandomPlayer extends YatzyPlayer {

    public RandomPlayer() {}

    public RandomPlayer(long seed, boolean log) {
        super(seed, log);
    }

    @Override
    protected Optional<Category> selectCategory(ScoreCard scoresheet, ThrowState state) {
        return new RandomSelection(new SelectionParams(state.getDices(), scoresheet)).select();
    }
}
