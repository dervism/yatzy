package no.nav.game;

import no.nav.model.Category;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

class RandomPlayerTest {

    @Test
    void play() {

        RandomPlayer player = new RandomPlayer(12345678, true);

        HashMap<Category, Integer> scoresheet = new HashMap<>();
        //scoresheet.put(Category.ONE, 0);
        //scoresheet.put(Category.TWO, 0);
        //scoresheet.put(Category.THREE, 0);
        //scoresheet.put(Category.FOUR, 0);
        //scoresheet.put(Category.FIVE, 0);
        //scoresheet.put(Category.SIX, 0);

        player.play(scoresheet);
    }

    @Test
    void play2() {

        RandomPlayer player = new RandomPlayer(1437231842, true);

        HashMap<Category, Integer> scoresheet = new HashMap<>();
        scoresheet.put(Category.ONE, 0);
        //scoresheet.put(Category.TWO, 0);
        //scoresheet.put(Category.THREE, 0);
        scoresheet.put(Category.FOUR, 0);
        //scoresheet.put(Category.FIVE, 0);
        //scoresheet.put(Category.SIX, 0);

        player.play(scoresheet);
    }
}