package com.virtualSlime.Utils;

import com.virtualSlime.Entity.User;
import lombok.*;

import java.util.Date;

@Data
@ToString
@AllArgsConstructor
public class UserVerificationWrapper {
    private User user;
    private Date startedTime;
    private String code;
    private Boolean isChecking;
}
