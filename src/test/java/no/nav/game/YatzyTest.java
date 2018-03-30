package no.nav.game;

import no.nav.game.player.RandomPlayer;
import no.nav.game.player.YatzyPlayer;
import org.junit.jupiter.api.Test;

class YatzyTest {

    @Test
    void play() {
        Game player = new Yatzy(new YatzyPlayer());
        player.play();

        System.out.println();

        Game random = new Yatzy(new RandomPlayer());
        random.play();
    }
}