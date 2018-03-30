package no.nav;

import java.util.Collection;

public class Util {

    public static Integer max(Collection<Integer> list) {
        return list.stream().max(Integer::compareTo).orElseThrow(IllegalStateException::new);
    }

    public static Integer min(Collection<Integer> list) {
        return list.stream().min(Integer::compareTo).orElseThrow(IllegalStateException::new);
    }

    public static int sum(Collection<Integer> list) {
        return list.stream().mapToInt(Integer::intValue).sum();
    }
}
