package qlpk.entity.enums;

public enum Role {

    BACSY("BACSY",0), YTA("YTA",1), ADMIN("ADMIN", 2);
    private final String type;
    private final Integer value;
    Role(String type, int value) {
        this.type = type;
        this.value = value;
    }

    public String getType() {
        return type;
    }

    public Integer getValue() {
        return value;
    }
}
