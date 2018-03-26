package no.nav.game;

import no.nav.model.Category;
import no.nav.model.ThrowState;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

public class Yatzy implements Game {

    private int nrOfGames = 100000;

    private Player player;

    public Yatzy(Player player) {
        this.player = player;
    }

    @Override
    public Map<Category, Double> play() {

        Map<Category, Double> total = new HashMap<>(6);
        Arrays.stream(Category.values()).forEach(category -> total.put(category, 0D));

        IntStream.range(0, nrOfGames).forEach(round -> {
            Map<Category, Integer> scoreSheet = new HashMap<>(6);

            IntStream.range(0, 6).forEach(i -> {

                ThrowState score = player.play(scoreSheet);

                // log score
                scoreSheet.put(score.getCategory(), score.getSum());
                total.put(score.getCategory(), total.get(score.getCategory()) + score.getSum());
            });
        });

        logGameSummary(total);

        return total;
    }

    @Override
    public double score(Map<Category, Double> total) {
        return total.values()
                .stream()
                .map(value -> value / nrOfGames)
                .reduce((d1, d2) -> d1 + d2)
                .orElse(0D);
    }

    private void logGameSummary(Map<Category, Double> total) {
        System.out.println("total " + total);
        System.out.println("Total of averages for " + player + ": " + score(total));
    }

}
