package no.nav.game.player;

import no.nav.Util;
import no.nav.http.Dice;
import no.nav.http.DiceLocal;
import no.nav.model.Category;
import no.nav.model.ScoreCard;
import no.nav.model.ThrowState;
import no.nav.model.maximizer.FrequencyMaximizer;
import no.nav.model.maximizer.Maximizer;
import no.nav.model.selection.MaxValueSelection;
import no.nav.model.selection.MinValueSelection;
import no.nav.model.selection.SortedSelection;

import java.util.List;
import java.util.Optional;

public class YatzyPlayer implements Player {

    private Maximizer maximizer;
    private Dice dice;
    private boolean log;
    private final int nrOfDice = 5;

    public YatzyPlayer() {
        this(new FrequencyMaximizer(), new DiceLocal(System.currentTimeMillis()), false);
    }

    public YatzyPlayer(long seed, boolean log) {
        this(new FrequencyMaximizer(), new DiceLocal(seed), log);
    }

    public YatzyPlayer(Maximizer maximizer, Dice dice, boolean log) {
        this.maximizer = maximizer;
        this.dice = dice;
        this.log = log;
    }

    @Override
    public List<Integer> throwDice(int numberOfDice) {
        return dice.roll(numberOfDice);
    }

    @Override
    public ThrowState play(ScoreCard scoresheet) {

        ThrowState firstThrow = firstThrow(scoresheet);

        ThrowState secondThrow = secondThrow(scoresheet, firstThrow);

        return thirdThrow(scoresheet, firstThrow, secondThrow);
    }

    private ThrowState firstThrow(ScoreCard scoresheet) {
        ThrowState firstThrow = new ThrowState(throwDice(nrOfDice));
        Category firstCategory = selectCategory(scoresheet, firstThrow).orElse(scoresheet.anyCategory());
        setCategorySum(firstThrow, firstCategory, Util.sum(firstThrow.filterDices(firstCategory.index())));

        if (log) {
            System.out.println("firstCategory: " + firstCategory);
            System.out.println(firstThrow);
        }
        return firstThrow;
    }

    private ThrowState secondThrow(ScoreCard scoresheet, ThrowState firstThrow) {
        ThrowState secondThrow = new ThrowState(firstThrow);

        List<Integer> selectedDice = firstThrow.filterDices(firstThrow.getCategory().index());
        selectedDice.addAll(throwDice(nrOfDice - selectedDice.size()));
        secondThrow.setDices(selectedDice);

        Category secondCategory = selectCategory(scoresheet, secondThrow).orElse(scoresheet.anyCategory());
        secondCategory = maximizer.maximize(secondCategory, secondThrow, scoresheet);
        setCategorySum(secondThrow, secondCategory, Util.sum(secondThrow.filterDices(secondCategory.index())));

        if (log) {
            System.out.println("secondCategory: " + secondCategory);
            System.out.println(secondThrow);
        }
        return secondThrow;
    }

    private ThrowState thirdThrow(ScoreCard scoresheet, ThrowState firstThrow, ThrowState secondThrow) {
        ThrowState thirdThrow = new ThrowState(secondThrow);

        List<Integer> selectedDice = secondThrow.filterDices(firstThrow.getCategory().index(), secondThrow.getCategory().index());
        selectedDice.addAll(throwDice(nrOfDice - selectedDice.size()));
        thirdThrow.setDices(selectedDice);

        Category thirdCategory = selectCategory(scoresheet, thirdThrow).orElse(scoresheet.anyCategory());
        thirdCategory = maximizer.maximize(thirdCategory, thirdThrow, scoresheet);
        setCategorySum(thirdThrow, thirdCategory, Util.sum(thirdThrow.filterDices(thirdCategory.index())));

        if (log) {
            System.out.println("thirdCategory: " + thirdCategory);
            System.out.println(thirdThrow);
        }
        return thirdThrow;
    }

    private void setCategorySum(ThrowState state, Category category, int sum) {
        state.setCategory(category);
        state.setSum(sum);
    }

    protected Optional<Category> selectCategory(ScoreCard scoresheet, ThrowState state) {
        return new SortedSelection(state.getDices(), scoresheet)
                .orElse(new MaxValueSelection(), new MinValueSelection());
    }

    public String toString() {
        return "YatzyPlayer{}";
    }
}
