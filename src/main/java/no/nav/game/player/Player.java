package no.nav.game.player;

import no.nav.model.ScoreCard;
import no.nav.model.ThrowState;

import java.util.List;

public interface Player {

    List<Integer> throwDice(int numberOfDice);

    ThrowState play(ScoreCard scoresheet);

}
