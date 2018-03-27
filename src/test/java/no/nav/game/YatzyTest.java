package no.nav.game;

import org.junit.jupiter.api.Test;

class YatzyTest {

    @Test
    void play() {
        Yatzy sorted = new Yatzy(new SortedPlayer());
        sorted.play();

        System.out.println();

        Yatzy random = new Yatzy(new RandomPlayer());
        random.play();
    }
}