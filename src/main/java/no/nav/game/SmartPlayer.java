package no.nav.game;

import no.nav.http.Dice;
import no.nav.http.DiceLocal;
import no.nav.model.Category;
import no.nav.model.ThrowState;
import no.nav.model.selection.MaxValueSelection;
import no.nav.model.selection.SortedSelection;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public class SmartPlayer extends AbstractPlayer {

    private Dice dice =  new DiceLocal();

    @Override
    public List<Integer> throwDice(int numberOfDice) {
        return dice.roll(numberOfDice);
    }

    @Override
    public Optional<Category> getSelectionStrategyHasMatch(Map<Category, Integer> game, ThrowState state) {
        return new SortedSelection()
                .orElse(state.getDices(), game.keySet(),
                        new MaxValueSelection());
    }

    @Override
    public String toString() {
        return "SmartPlayer{}";
    }
}
