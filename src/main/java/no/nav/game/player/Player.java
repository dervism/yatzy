package no.nav.game.player;

import no.nav.model.ScoreSheet;
import no.nav.model.ThrowState;

import java.util.List;

public interface Player {

    List<Integer> throwDice(int numberOfDice);

    ThrowState play(ScoreSheet scoresheet);

}
