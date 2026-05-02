package org.example;

/**
 * Перерахування для кольорів телефонів.
 */
public enum Color {
    BLACK("Чорний"),
    WHITE("Білий"),
    SILVER("Сріблястий"),
    GOLD("Золотистий"),
    BLUE("Синій"),
    RED("Червоний"),
    GREEN("Зелений");

    private final String displayName;

    Color(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    @Override
    public String toString() {
        return displayName;
    }
}
