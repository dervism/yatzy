package no.nav.model.selection;

import no.nav.model.ScoreCard;

import java.util.Collection;

public class SelectionParams {
    private Collection<Integer> diceList;
    private ScoreCard scoreCard;

    public SelectionParams(Collection<Integer> diceList, ScoreCard scoreCard) {
        this.diceList = diceList;
        this.scoreCard = scoreCard;
    }

    public static SelectionParams of(Collection<Integer> diceList, ScoreCard scoreCard) {
        return new SelectionParams(diceList, scoreCard);
    }

    public Collection<Integer> getDiceList() {
        return diceList;
    }

    public ScoreCard getScoreCard() {
        return scoreCard;
    }
}
