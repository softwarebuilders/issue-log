package cz.softwarebuilders.issuelog.persistence.entity;

public enum Priority {
    BLOCK(1),
    HIGH(2),
    MIDDLE(3),
    LOW(4),
    NONE(5);

    private final int code;

    private Priority(int code) {
        this.code = code;
    }

    public static Priority fromCode(int code) {
        for (Priority p : values()) {
            if (p.code == code) {
                return p;
            }
        }
        throw new RuntimeException("Uknown priority with code " + code);
    }

    public int getCode() {
        return code;
    }

}
