package no.nav.game;

import no.nav.model.Category;

import java.util.Map;

public interface Game {

    Map<Category, Double> play();

    double score(Map<Category, Double> total);
}
