package no.nav.game;

import org.junit.jupiter.api.Test;

class YatzyTest {

    @Test
    void play() {
        Yatzy player = new Yatzy(new YatzyPlayer());
        player.play();
    }
}