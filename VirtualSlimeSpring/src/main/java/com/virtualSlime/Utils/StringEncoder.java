package com.virtualSlime.Utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class StringEncoder {

    /**
     * userPasswordEncode for encoding user password before inserting into the database
     * @param userPassword, passed in from register module
     * @return encoded user password
     */
    public static String userPasswordEncode(String userPassword, long timestamp){
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(10);
        return bCryptPasswordEncoder.encode(userPassword);
    }

    /*public static String userPasswordDecode(String password){

    }*/

}
