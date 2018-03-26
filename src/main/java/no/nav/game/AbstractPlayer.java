package no.nav.game;

import no.nav.Util;
import no.nav.model.Category;
import no.nav.model.ThrowState;
import no.nav.model.selection.RandomSelection;

import java.util.*;
import java.util.stream.Collectors;

import static no.nav.Util.sum;
import static no.nav.model.ThrowState.hasNoMoreThrows;
import static no.nav.model.ThrowState.isMaxScore;

public abstract class AbstractPlayer implements Player {

    protected int numberOfDice;

    public AbstractPlayer() {
        this.numberOfDice = 5;
    }

    public AbstractPlayer(int numberOfDice) {
        this.numberOfDice = numberOfDice;
    }

    @Override
    public ThrowState play(Map<Category, Integer> game) {
        ThrowState state = new ThrowState(throwDice(getNumberOfDice()));

        // check if the dice can be matched to any category
        if (Util.availableCategories(state.getDices(), game.keySet()).isEmpty()) {
            // no match, play a random category and rethrow.
            ThrowState nextState = new ThrowState(state);
            nextState.setDices(throwDice(getNumberOfDice()));
            nextState.setCategory(getSelectionStrategyNoMatch(game, nextState));
            return nextState;
        }

        Optional<Category> category = getSelectionStrategyHasMatch(game, state);
        state.setCategory(category.get());

        return rethrow(state);
    }

    protected abstract Optional<Category> getSelectionStrategyHasMatch(Map<Category, Integer> game, ThrowState state);

    public ThrowState rethrow(ThrowState state) {

        //logThrow(state);

        Category category = state.getCategory();

        // more throws?
        if (hasNoMoreThrows(state)) {
            state.append(sum(state.filterDices(category.index())));
            state.setDices(Collections.emptyList());
            //logPartialState(state);
            return state;
        }

        // max score?
        if (isMaxScore(state, getNumberOfDice())) {
            return state;
        }

        // calc current sum (from input)
        List<Integer> filteredList = state.filterDices(category.index());
        ThrowState nextState = new ThrowState(state);
        nextState.append(sum(filteredList));

        // rethrow the remaining dice
        nextState.setDices(throwDice(state.getDices().size() - filteredList.size()));

        return rethrow(nextState);
    }

    public Category getSelectionStrategyNoMatch(Map<Category, Integer> game, ThrowState state) {
        List<Category> notYetSelectedCategories = Arrays.stream(Category.values())
                .filter(category -> !game.keySet().contains(category))
                .collect(Collectors.toList());
        return RandomSelection.random(notYetSelectedCategories);
    }

    private void logPartialState(ThrowState state) {
        System.out.println(state.getCategory() + " final state: " + state);
    }

    private void logThrow(ThrowState state) {
        System.out.println("history " + state.getCategory() + state.getThrowNr()+ " " + state.getHistory());
    }

    public int getNumberOfDice() {
        return numberOfDice;
    }
}
