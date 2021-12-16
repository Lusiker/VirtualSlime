package com.virtualSlime.Enum.EntityType;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.baomidou.mybatisplus.annotation.IEnum;

public enum ReportType implements IEnum<Byte> {
    UNDEFINED(0),
    ITEM(1),
    USER(2),
    COMMENT(3);

    @EnumValue
    private final Byte value;

    ReportType(int value) {
        this.value = (byte)value;
    }

    @Override
    public Byte getValue() {
        return this.value;
    }
}
