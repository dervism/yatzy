package no.nav.decisions;

public interface Rule<R, S> {

    R evaluate(S state);

}
