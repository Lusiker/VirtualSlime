package com.virtualSlime.Enum;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.baomidou.mybatisplus.annotation.IEnum;

public enum MessageType implements IEnum<Byte> {
    UNDEFINED(0),
    TEXT(1),
    PICTURE(2);

    @EnumValue
    private final Byte value;

    MessageType(int value) {
        this.value = (byte)value;
    }

    @Override
    public Byte getValue() {
        return this.value;
    }
}
