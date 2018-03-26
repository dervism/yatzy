package no.nav.model;

public enum Category {
    ONE, TWO, THREE, FOUR, FIVE, SIX;

    public int index() {
        return this.ordinal() + 1;
    }

    public static Category fromIndex(int index) {
        Category[] values = values();
        if (index < 0 || index > values.length ) throw new IllegalStateException();
        return values[--index];
    }

}
