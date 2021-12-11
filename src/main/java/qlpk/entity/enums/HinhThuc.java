package qlpk.entity.enums;

public enum HinhThuc {
    TIEPDON("tiepdon", 1),
    CAPTHUOC("capthuoc", 2),
    TIEMTHUOC("tiemthuoc", 3),
    CHAMSOC("chamsoc", 4),
    THEODOI("theodoi", 5);
    private final String type;
    private final int value;

    HinhThuc(String type, int value) {
        this.type = type;
        this.value = value;
    }

    public String getType() {
        return type;
    }

    public int getValue() {
        return value;
    }
}
