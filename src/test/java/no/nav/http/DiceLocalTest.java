package no.nav.http;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

class DiceLocalTest {

    @Test
    void roll() {
        DiceLocal roll = new DiceLocal();

        List<Integer> list = roll.roll(6);
        assertTrue(list.size() == 6);
        System.out.println("Dice:");
        System.out.println(list);

        assertTrue(roll.roll(3).size() == 3);
        assertTrue(roll.roll(1).size() == 1);
    }
}