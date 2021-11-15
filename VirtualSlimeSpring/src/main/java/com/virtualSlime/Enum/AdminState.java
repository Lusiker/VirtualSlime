package com.virtualSlime.Enum;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.baomidou.mybatisplus.annotation.IEnum;

public enum AdminState implements IEnum<Byte> {
    NORMAL(0),
    RESTRICTED(1),
    BANNED(2),
    LOGOFF(3),
    SUPREME(4);

    @EnumValue
    private final Byte value;

    AdminState(int value) {
        this.value = (byte)value;
    }

    @Override
    public Byte getValue() {
        return this.value;
    }
}
