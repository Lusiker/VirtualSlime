package com.virtualSlime.Enum;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.baomidou.mybatisplus.annotation.IEnum;

public enum MessageState implements IEnum<Byte> {
    UNDEFINED(0),
    SUCCESS(1),
    FAILED(2),
    HIDDEN(3);

    @EnumValue
    private final Byte value;

    MessageState(int value) {
        this.value = (byte)value;
    }

    @Override
    public Byte getValue() {
        return this.value;
    }
}
