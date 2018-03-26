package no.nav.decisions;

import static java.util.Objects.requireNonNull;

public abstract class DecisionRule<R, S> implements Rule<R, S> {

    private final Rule<R, S> no;
    private final Rule<R, S> yes;

    public DecisionRule(Rule<R, S> no, Rule<R, S> yes) {

        requireNonNull(no);
        requireNonNull(yes);

        this.no = no;
        this.yes = yes;
    }

    @Override
    public R evaluate(S state) {
        return internalEvaluate(state) ? yes(state) : no(state);
    }

    private R no(S state) {
        return no.evaluate(state);
    }

    private R yes(S state) {
        return yes.evaluate(state);
    }

    protected abstract boolean internalEvaluate(S state);
}
