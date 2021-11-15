package com.virtualSlime.Enum;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.baomidou.mybatisplus.annotation.IEnum;

public enum UserSex implements IEnum<Byte> {
    UNDEFINED(0),
    MALE(1),
    FEMALE(2),
    SECRET(3);

    @EnumValue
    private final Byte value;

    UserSex(int value) {
        this.value = (byte)value;
    }

    @Override
    public Byte getValue() {
        return this.value;
    }
}
