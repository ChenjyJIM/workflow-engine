package com.graduate.engine.enums;

public enum PriorityEnum {
    LOW_PRIORITY("低", 1),
    NORMAL_PRIORITY("中", 5),
    HIGH_PRIORITY("高", 10);

    private String desc;
    private int code;

    PriorityEnum(String desc, int code) {
        this.desc = desc;
        this.code = code;
    }

    public static PriorityEnum getByCode(Integer code) {
        for (PriorityEnum compensateOpEnum : values()) {
            if (compensateOpEnum.code().equals(code)) {
                return compensateOpEnum;
            }
        }
        return null;
    }

    public Integer code() {
        return this.code;
    }

    public String desc() {
        return this.desc;
    }
}
