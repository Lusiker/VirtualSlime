package com.virtualSlime.Service;

import java.util.Arrays;

public class PasswordSimplicityChecker {
    private static final char[] ascChar = {
            '!','"','#','$','%','&','\'','(',')',
            '*','+',',','-','.','/',':',';','<','>',
            '=','?','@','[',']','^','_','`','{','}',
            '|','~'
    };

    private static boolean isLowerLetter(char c){
        return c >= 'a' && c <= 'z';
    }

    private static boolean isUpperLetter(char c){
        return c >= 'A' && c <= 'Z';
    }

    private static boolean isNumber(char c){
        return c >= '0' && c <= '9';
    }

    private static boolean isAscChar(char c){
        for(char c0 : ascChar){
            if(c == c0){
                return true;
            }
        }

        return false;
    }

    /**
     * The standard to tell if the password is too simple is whether
     * the password contains 2 or more types of char in lower letter, upper letter, number, punctuation
     * @param password - a String that contains only char elements!
     * @return boolean - whether the password is too simple
     */
    public static boolean checkPasswordSimplicity(String password){
        if(password.length() <= 8){
            return false;
        }

        int[] checkerArray = new int[4];
        for(int i = 0;i < 4;i++){
            checkerArray[i] = 0;
        }

        for(char c : password.toCharArray()){
            if(isLowerLetter(c)){
                if(checkerArray[0] == 0){
                    checkerArray[0]++;
                }
            }else if(isUpperLetter(c)){
                if(checkerArray[1] == 0){
                    checkerArray[1]++;
                }
            }else if(isNumber(c)){
                if(checkerArray[2] == 0){
                    checkerArray[2]++;
                }
            }else if(isAscChar(c)){
                if(checkerArray[3] == 0){
                    checkerArray[3]++;
                }
            }
        }

        int simplicity = 0;
        for(int i : checkerArray){
            if(i > 0){
                simplicity++;
            }
        }

        return simplicity >= 2;
    }
}
