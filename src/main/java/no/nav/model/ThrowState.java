package no.nav.model;

import java.util.*;
import java.util.stream.Collectors;

public class ThrowState {

    private int sum;

    private int throwNr;

    private Category category;

    private List<Integer> dices;

    private LinkedList<ThrowState> history;

    public ThrowState() {
        this(0, 0, null);
    }

    public ThrowState(List<Integer> dices) {
        this(0, 1, dices);
    }

    public ThrowState(int sum, int throwNr, List<Integer> dices) {
        this.sum = sum;
        this.throwNr = throwNr;
        this.dices = Objects.isNull(dices) ? new ArrayList<>(6) : dices;
        this.history = new LinkedList<>();
    }

    public ThrowState(ThrowState anotherState) {
        this.sum = anotherState.sum;
        this.throwNr = anotherState.throwNr + 1;
        this.dices = new ArrayList<>(anotherState.dices);
        this.category = anotherState.getCategory();
        this.history = new LinkedList<>(anotherState.history);
        this.history.add(anotherState);
    }

    public int getSum() {
        return sum;
    }

    public void setSum(int sum) {
        this.sum = sum;
    }

    public void append(int n) {
        setSum(getSum() + n);
    }

    public int getThrowNr() {
        return throwNr;
    }

    public List<Integer> getDices() {
        return dices;
    }

    public boolean isYatzy() {
        return getDices().stream().distinct().count() == 1;
    }

    public List<Integer> filterDices(int... n) {
        return getDices()
                .stream()
                .filter(dice -> Arrays.stream(n).anyMatch(value -> value == dice))
                .collect(Collectors.toList());
    }

    public static boolean isMaxScore(ThrowState state, int dice) {
        return state.getSum() == (state.getCategory().index() * dice);
    }

    public static boolean hasNoMoreThrows(ThrowState state) {
        return state.getThrowNr() >= 3;
    }

    public LinkedList<ThrowState> getHistory() {
        return history;
    }

    public void setDices(List<Integer> dices) {
        this.dices = dices;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "ThrowState{" +
                "sum=" + sum +
                ", throwNr=" + throwNr +
                ", category=" + category +
                ", dices=" + dices +
                ", history=" + history +
                '}';
    }
}
