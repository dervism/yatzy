package no.nav.decisions;

import java.util.function.Function;

import static java.util.Objects.requireNonNull;

public abstract class AndRule<S> implements Rule<Boolean, S> {

    private final Function<S, Boolean> first;
    private final Function<S, Boolean> second;

    public AndRule(Function<S, Boolean> first, Function<S, Boolean> second) {
        requireNonNull(first);
        requireNonNull(second);

        this.first = first;
        this.second = second;
    }

    @Override
    public Boolean evaluate(S state) {
        return first.apply(state) && second.apply(state);
    }

}
