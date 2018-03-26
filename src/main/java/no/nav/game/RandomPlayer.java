package no.nav.game;

import no.nav.http.Dice;
import no.nav.http.DiceLocal;
import no.nav.model.Category;
import no.nav.model.ThrowState;
import no.nav.model.selection.RandomSelection;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public class RandomPlayer extends AbstractPlayer {

    private Dice dice =  new DiceLocal();

    @Override
    public List<Integer> throwDice(int numberOfDice) {
        return dice.roll(numberOfDice);
    }

    @Override
    public Optional<Category> getSelectionStrategyHasMatch(Map<Category, Integer> game, ThrowState state) {
        return new RandomSelection().select(state.getDices(), game.keySet());
    }

    @Override
    public String toString() {
        return "RandomPlayer{}";
    }
}
