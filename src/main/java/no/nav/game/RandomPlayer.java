package no.nav.game;

import no.nav.Util;
import no.nav.http.Dice;
import no.nav.http.DiceLocal;
import no.nav.model.Category;
import no.nav.model.ThrowState;
import no.nav.model.maximizer.FrequencyMaximizer;
import no.nav.model.maximizer.Maximizer;
import no.nav.model.selection.EqualSelection;
import no.nav.model.selection.MinValueSelection;
import no.nav.model.selection.RandomSelection;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public class RandomPlayer implements Player {

    private final Maximizer maximizer = new FrequencyMaximizer();
    private Dice dice;
    private boolean log;

    public RandomPlayer() {
        log = false;
        dice =  new DiceLocal();
    }

    public RandomPlayer(long seed, boolean log) {
        this.dice = new DiceLocal(seed);
        this.log = log;
    }



    public String toString() {
        return "RandomPlayer{}";
    }

    @Override
    public List<Integer> throwDice(int numberOfDice) {
        return dice.roll(numberOfDice);
    }

    @Override
    public ThrowState play(Map<Category, Integer> scoresheet) {
        List<Category> selectableCategories = Util.selectableCategories(scoresheet.keySet());

        ThrowState firstThrow = firstThrow(scoresheet, selectableCategories);

        ThrowState secondThrow = secondThrow(scoresheet, selectableCategories, firstThrow);

        ThrowState thirdThrow = thirdThrow(scoresheet, selectableCategories, firstThrow, secondThrow);

        return thirdThrow;
    }

    private ThrowState firstThrow(Map<Category, Integer> scoresheet, List<Category> selectableCategories) {
        ThrowState firstThrow = new ThrowState(throwDice(5));
        Category firstCategory = getCategory(scoresheet, firstThrow).orElse(selectableCategories.get(0));
        //firstCategory = maximizer.maximize(firstCategory, firstThrow, scoresheet);
        firstThrow.setCategory(firstCategory);
        firstThrow.setSum(Util.sum(firstThrow.filterDices(firstCategory.index())));

        if (log) {
            System.out.println("firstCategory: " + firstCategory);
            System.out.println(firstThrow);
        }
        return firstThrow;
    }

    private ThrowState secondThrow(Map<Category, Integer> scoresheet, List<Category> selectableCategories, ThrowState firstThrow) {
        ThrowState secondThrow = new ThrowState(firstThrow);

        List<Integer> list2 = firstThrow.filterDices(firstThrow.getCategory().index());
        list2.addAll(throwDice(5 - list2.size()));
        secondThrow.setDices(list2);

        Category secondCategory = getCategory(scoresheet, secondThrow).orElse(selectableCategories.get(0));
        secondCategory = maximizer.maximize(secondCategory, secondThrow, scoresheet);
        secondThrow.setCategory(secondCategory);
        secondThrow.setSum(Util.sum(secondThrow.filterDices(secondCategory.index())));

        if (log) {
            System.out.println("secondCategory: " + secondCategory);
            System.out.println(secondThrow);
        }
        return secondThrow;
    }

    private ThrowState thirdThrow(Map<Category, Integer> scoresheet, List<Category> selectableCategories, ThrowState firstThrow, ThrowState secondThrow) {
        ThrowState thirdThrow = new ThrowState(secondThrow);

        List<Integer> list3 = secondThrow.filterDices(firstThrow.getCategory().index(), secondThrow.getCategory().index());
        list3.addAll(throwDice(5 - list3.size()));
        thirdThrow.setDices(list3);

        Category thirdCategory = getCategory(scoresheet, thirdThrow).orElse(selectableCategories.get(0));
        //thirdCategory = maximizer.maximize(thirdCategory, thirdThrow, scoresheet);
        thirdThrow.setCategory(thirdCategory);
        thirdThrow.setSum(Util.sum(thirdThrow.filterDices(thirdCategory.index())));

        if (log) {
            System.out.println("thirdCategory: " + thirdCategory);
            System.out.println(thirdThrow);
        }
        return thirdThrow;
    }

    private Optional<Category> getCategory(Map<Category, Integer> scoresheet, ThrowState state) {
        return new EqualSelection(state.getDices(), scoresheet.keySet())
                    .orElse(new MinValueSelection(),
                            new RandomSelection());
    }
}
