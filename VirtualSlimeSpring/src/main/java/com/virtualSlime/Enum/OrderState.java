package com.virtualSlime.Enum;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.baomidou.mybatisplus.annotation.IEnum;

public enum OrderState implements IEnum<Byte> {
    UNDEFINED(0),
    CREATED(1),
    FINISHED(2),
    CLOSED(3);

    @EnumValue
    private final Byte value;

    OrderState(int value) {
        this.value = (byte)value;
    }

    @Override
    public Byte getValue() {
        return this.value;
    }
}
