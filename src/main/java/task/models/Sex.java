package task.models;

public enum Sex {
    MALE,
    FEMALE;

    @Override
    public String toString(){
        return this.name().toLowerCase();
    }

    public static Sex parseSex(String stringSex) {
        return switch (stringSex) {
            case ("female") -> Sex.FEMALE;
            case ("male") -> Sex.MALE;
            case ("null") -> null;
            default -> throw new RuntimeException("Unknown sex");
        };
    }
}
