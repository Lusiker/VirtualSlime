package com.virtualSlime.Enum.EntityType;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.baomidou.mybatisplus.annotation.IEnum;

public enum CouponType implements IEnum<Byte> {
    NOT_DEFINED(0),
    MINUS(1),
    MULTIPLE(2);

    @EnumValue
    private final Byte value;

    CouponType(int value) {
        this.value = (byte)value;
    }

    @Override
    public Byte getValue() {
        return this.value;
    }
}
