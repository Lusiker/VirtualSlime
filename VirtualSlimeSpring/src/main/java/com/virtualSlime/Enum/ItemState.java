package com.virtualSlime.Enum;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.baomidou.mybatisplus.annotation.IEnum;

public enum ItemState implements IEnum<Byte> {
    UNDEFINED(-1),
    NORMAL(0),
    PROMOTED(1),
    HIDDEN(2);

    @EnumValue
    private final Byte value;

    ItemState(int value) {
        this.value = (byte)value;
    }

    @Override
    public Byte getValue() {
        return this.value;
    }
}
