package no.nav.game;

import no.nav.model.Category;
import no.nav.model.ThrowState;

import java.util.List;
import java.util.Map;

public interface Player {

    List<Integer> throwDice(int numberOfDice);

    ThrowState play(Map<Category, Integer> scoresheet);

}
