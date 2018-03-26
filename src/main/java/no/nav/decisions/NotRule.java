package no.nav.decisions;

import java.util.function.Function;

import static java.util.Objects.requireNonNull;

public abstract class NotRule<S> implements Rule<Boolean, S> {

    private final Function<S, Boolean> first;

    public NotRule(Function<S, Boolean> first) {
        requireNonNull(first);

        this.first = first;
    }

    @Override
    public Boolean evaluate(S state) {
        return !first.apply(state);
    }


}
