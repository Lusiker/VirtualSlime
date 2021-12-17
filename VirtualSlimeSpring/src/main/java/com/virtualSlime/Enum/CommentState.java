package com.virtualSlime.Enum;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.baomidou.mybatisplus.annotation.IEnum;

public enum CommentState implements IEnum<Byte> {
    NORMAL(0),
    RESTRICTED(1),
    HIDDEN(2);

    @EnumValue
    private final Byte value;

    CommentState(int value){
        this.value = (byte)value;
    }

    @Override
    public Byte getValue(){
        return this.value;
    }

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
}
