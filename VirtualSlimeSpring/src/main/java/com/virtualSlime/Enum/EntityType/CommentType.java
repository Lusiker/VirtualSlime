package com.virtualSlime.Enum.EntityType;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.baomidou.mybatisplus.annotation.IEnum;

public enum CommentType implements IEnum<Byte> {
    UNDEFINED(0),
    RATING(1),
    REPLY(2);

    @EnumValue
    private final Byte value;

    CommentType(int value) {
        this.value = (byte)value;
    }

    @Override
    public Byte getValue() {
        return this.value;
    }
}
