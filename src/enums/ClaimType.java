package enums;

public enum ClaimType {
    CAR("Car Accident"),
    HOME("Home Damage"),
    HEALTH("Illness");

    private final String displayName;

    ClaimType(String displayName) {
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
