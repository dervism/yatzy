package no.nav.http;

import org.apache.commons.math3.random.MersenneTwister;

import java.security.SecureRandom;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class DiceLocal implements Dice {

    private Dice random;

    public DiceLocal() {
        random = new MersenneTwisterRandom(); // much faster than java.util.Random
    }

    public DiceLocal(long seed) {
        random = new MersenneTwisterRandom(seed); // much faster than java.util.Random
    }

    @Override
    public List<Integer> roll(int n) {
        return random.roll(n);
    }


    public static class MersenneTwisterRandom implements Dice {
        private MersenneTwister twister;

        public MersenneTwisterRandom() {
            twister = new MersenneTwister();
        }

        public MersenneTwisterRandom(long seed) {
            twister = new MersenneTwister(seed);
        }

        @Override
        public List<Integer> roll(int n) {
            return IntStream.range(0, n).mapToObj(dice -> (1 + twister.nextInt(6))).collect(Collectors.toList());
        }

        public int nextInt(int n, int k) {
            return n + twister.nextInt(k);
        }
    }

    public static class JavaSecureRandom implements Dice {
        private SecureRandom random;

        public JavaSecureRandom() {
            random = new SecureRandom();
        }

        public JavaSecureRandom(byte[] seed) {
            random = new SecureRandom(seed);
        }

        @Override
        public List<Integer> roll(int n) {
            return random.ints(n, 1, 7).boxed().collect(Collectors.toList());
        }

    }

}
