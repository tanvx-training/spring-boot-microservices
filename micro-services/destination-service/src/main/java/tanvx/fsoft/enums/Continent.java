package tanvx.fsoft.enums;

public enum Continent {
    AFRICA("Africa"),
    ANTARCTICA("Antarctica"),
    ASIA("Asia"),
    EUROPE("Europe"),
    NORTH_AMERICA("North America"),
    SOUTH_AMERICA("South America"),
    AUSTRALIA("Australia");

    private final String displayName;

    Continent(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}

