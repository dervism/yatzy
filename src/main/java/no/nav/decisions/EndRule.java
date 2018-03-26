package no.nav.decisions;

public abstract class EndRule<R, S> implements Rule<R, S> {

    @Override
    public R evaluate(S state) {
        return conclude(state);
    }

    protected abstract R conclude(S state);
}
