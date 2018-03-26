package no.nav.decisions;

import static java.util.Objects.requireNonNull;

public abstract class DataRule<R, S> implements Rule<R, S> {

    private Rule<R, S> next;

    public DataRule(Rule<R, S> next) {
        requireNonNull(next);
        this.next = next;
    }

    @Override
    public R evaluate(S state) {
        update(state);
        return next(state);
    }

    /**
     * The logic to perform.
     *
     * @param state the state to evalute
     */
    protected abstract void update(S state);

    private R next(S state) {
        return next.evaluate(state);
    }
}
