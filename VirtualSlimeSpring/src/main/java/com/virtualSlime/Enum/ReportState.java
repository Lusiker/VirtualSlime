package com.virtualSlime.Enum;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.baomidou.mybatisplus.annotation.IEnum;

public enum ReportState implements IEnum<Byte> {
    CREATED(0),
    PROCESSING(2),
    FINISHED(3),
    CLOSED(4);

    @EnumValue
    private final Byte value;

    ReportState(int value) {
        this.value = (byte)value;
    }

    @Override
    public Byte getValue() {
        return this.value;
    }
}
