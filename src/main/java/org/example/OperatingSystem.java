package org.example;

/**
 * Перерахування для операційних систем телефонів.
 */
public enum OperatingSystem {
    ANDROID("Android"),
    IOS("iOS"),
    WINDOWS_PHONE("Windows Phone"),
    OTHER("Other");

    private final String name;

    OperatingSystem(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }
}
