package no.nav.game;

import no.nav.game.player.Player;
import no.nav.model.Category;
import no.nav.model.ScoreSheet;
import no.nav.model.ThrowState;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

public class Yatzy implements Game {

    Map<Category, Double> total;

    private int nrOfGames = 100_000;

    private Player player;

    public Yatzy(Player player) {
        this.player = player;
    }

    @Override
    public Map<Category, Double> play() {

        total = new HashMap<>(6);

        Arrays.stream(Category.values()).forEach(category -> total.put(category, 0D));

        IntStream.range(0, nrOfGames).forEach(round -> {
            ScoreSheet scoreSheet = new ScoreSheet();
            IntStream.range(0, 6).forEach(i -> updateScore(scoreSheet, player.play(scoreSheet)));
        });

        logGameSummary();

        return total;
    }

    private void updateScore(ScoreSheet scoreSheet, ThrowState score) {
        scoreSheet.put(score.getCategory(), score.getSum());
        total.computeIfPresent(score.getCategory(), (category, currentScore) -> currentScore + score.getSum());
    }

    @Override
    public double score() {
        return this.total.values()
                .stream()
                .map(value -> value / nrOfGames)
                .reduce((d1, d2) -> d1 + d2)
                .orElse(0D);
    }



    private void logGameSummary() {
        System.out.println("total " + this.total);
        System.out.println("Total of averages for " + player + ": " + score());
    }

}
