package no.nav.game;

import no.nav.Util;
import no.nav.http.Dice;
import no.nav.http.DiceLocal;
import no.nav.model.Category;
import no.nav.model.ThrowState;
import no.nav.model.maximizer.FrequencyMaximizer;
import no.nav.model.maximizer.Maximizer;

import java.util.*;
import java.util.stream.Collectors;

// bycount deretter bysum deretter byvalue

public class SortedPlayer implements Player {

    private final Maximizer maximizer = new FrequencyMaximizer();
    private Dice dice;
    private boolean log;

    public SortedPlayer() {
        log = false;
        dice =  new DiceLocal();
    }

    public SortedPlayer(long seed, boolean log) {
        this.dice = new DiceLocal(seed);
        this.log = log;
    }

    @Override
    public List<Integer> throwDice(int numberOfDice) {
        return dice.roll(numberOfDice);
    }

    @Override
    public ThrowState play(Map<Category, Integer> scoresheet) {

        // first
        ThrowState firstThrow = handleFirstThrow(scoresheet);

        // second
        List<Integer> filteredList = firstThrow.filterDices(firstThrow.getCategory().index());
        filteredList.addAll(throwDice(5 - filteredList.size()));
        ThrowState secondThrow = handleSecondThrow(scoresheet, firstThrow, filteredList);

        // third
        List<Integer> secondFilteredList = filter(secondThrow, Arrays.asList(firstThrow.getCategory(), secondThrow.getCategory()));
        secondFilteredList.addAll(throwDice(5 - secondFilteredList.size()));

        return handleFinalThrow(scoresheet, secondThrow, secondFilteredList);
    }

    private ThrowState handleFirstThrow(Map<Category, Integer> scoresheet) {
        ThrowState firstThrow = new ThrowState(throwDice(5));

        Category firstCategory = sort(count(scoresheet, firstThrow)).getFirst().getKey();
        firstThrow.setCategory(firstCategory);
        firstThrow.setSum(Util.sum(firstThrow.filterDices(firstCategory.index())));

        if (log) {
            System.out.println(firstThrow);
            System.out.println("firstCategory: " + firstCategory);
        }
        return firstThrow;
    }

    private ThrowState handleSecondThrow(Map<Category, Integer> scoresheet, ThrowState firstThrow, List<Integer> filteredList) {
        ThrowState secondThrow = new ThrowState(firstThrow);
        secondThrow.setDices(filteredList);

        LinkedList<Map.Entry<Category, Long>> secondList = sort(count(scoresheet, secondThrow));
        Category secondCategory = secondList.getFirst().getKey();

        secondCategory = maximizer.maximize(secondCategory, secondThrow, scoresheet);
        secondThrow.setCategory(secondCategory);
        secondThrow.setSum(Util.sum(secondThrow.filterDices(secondCategory.index())));

        if (log) {
            System.out.println(secondThrow);
            System.out.println("secondCategory: " + secondCategory);
        }
        return secondThrow;
    }

    private ThrowState handleFinalThrow(Map<Category, Integer> scoresheet, ThrowState secondThrow, List<Integer> secondFilteredList) {
        ThrowState thirdThrow = new ThrowState(secondThrow);
        thirdThrow.setDices(secondFilteredList);

        LinkedList<Map.Entry<Category, Long>> thirdList = sort(count(scoresheet, thirdThrow));
        Category thirdCategory = thirdList.getFirst().getKey();

        thirdCategory = maximizer.maximize(thirdCategory, thirdThrow, scoresheet);
        thirdThrow.setSum(Util.sum(thirdThrow.filterDices(thirdCategory.index())));
        thirdThrow.setCategory(thirdCategory);

        if (log) {
            System.out.println(thirdThrow);
            System.out.println("thirdCategory: " + thirdCategory);
        }
        return thirdThrow;
    }

    private List<Integer> filter(ThrowState state, List<Category> categories) {
        return state.getDices().stream()
                .map(Category::fromIndex)
                .filter(categories::contains)
                .map(Category::index)
                .collect(Collectors.toList());
    }

    private Map<Category, Long> count(Map<Category, Integer> scoresheet, ThrowState firstThrow) {
        return Arrays.stream(Category.values())
                .filter(category -> !scoresheet.keySet().contains(category))
                .collect(Collectors.toMap(o -> o, o -> firstThrow.getDices()
                        .stream().filter(die -> die == o.index()).count()));
    }

    private LinkedList<Map.Entry<Category, Long>> sort(Map<Category, Long> map) {
        return map.entrySet()
                .stream()
                .sorted(Comparator.comparing(Map.Entry<Category, Long>::getValue)
                        //.thenComparing(Comparator.comparingLong(o -> o.getKey().index() * o.getValue()))
                        .thenComparing(Comparator.comparingInt(o -> o.getKey().index())).reversed())
                .collect(Collectors.toCollection(LinkedList::new));
    }

    @Override
    public String toString() {
        return "SortedPlayer{}";
    }

}
