package com.virtualSlime.Enum;

import com.baomidou.mybatisplus.annotation.IEnum;

public enum AdminState implements IEnum<Integer> {
    NORMAL(0),
    RESTRICTED(1),
    BANNED(2),
    LOGOFF(3),
    SUPREME(4);

    private final Integer value;

    AdminState(Integer value) {
        this.value = value;
    }
    
    @Override
    public Integer getValue() {
        return this.value;
    }
}
