package com.virtualSlime.Utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class StringEncoder {
    private static final BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(10);

    /**
     * userPasswordEncode for encoding user password before inserting into the database
     * @param userPassword, passed in from register module
     * @return encoded user password
     */
    public static String userPasswordEncode(String userPassword){
        return bCryptPasswordEncoder.encode(userPassword);
    }

    public static boolean matchPassword(String newPassword, String SavedPassword){
        return bCryptPasswordEncoder.matches(newPassword,SavedPassword);
    }
}
