package task.models;

public enum Status {
    STUDIES,
    ON_ACADEMIC_LEAVE,
    EXPELLED;

    @Override
    public String toString(){
        return this.name().toLowerCase();
    }

    public static Status parseStatus(String stringStatus){
        return switch (stringStatus) {
            case ("studies") -> Status.STUDIES;
            case ("on_academic_vacation") -> Status.ON_ACADEMIC_LEAVE;
            case ("expelled") -> Status.EXPELLED;
            default -> throw new RuntimeException("Unknown status");
        };
    }
}
