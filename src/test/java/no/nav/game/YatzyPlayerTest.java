package no.nav.game;

import no.nav.game.player.YatzyPlayer;
import no.nav.model.ScoreCard;
import org.junit.jupiter.api.Test;

import static no.nav.model.Category.FOUR;
import static no.nav.model.Category.ONE;

class YatzyPlayerTest {

    @Test
    void play() {

        YatzyPlayer player = new YatzyPlayer(12345678, true);
        player.play(new ScoreCard());
    }

    @Test
    void play2() {

        YatzyPlayer player = new YatzyPlayer(1437231842, true);
        player.play(ScoreCard.of(ONE, FOUR));
    }
}