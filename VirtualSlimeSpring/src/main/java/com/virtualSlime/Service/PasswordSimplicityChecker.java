package com.virtualSlime.Service;

public class PasswordSimplicityChecker {
    private static final char[] ascChar = {
            '!','"','#','$','%','&','\'','(',')',
            '*','+',',','-','.','/',':',';','<','>',
            '=','?','@','[',']','^','_','`','{','}',
            '|','~'
    };

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
            return true;
        }

        int[] checkerArray = new int[4];
        for(char c : password.toCharArray()){
            if(Character.isLowerCase(c)){
                if(checkerArray[0] == 0){
                    checkerArray[0]++;
                }
            }else if(Character.isUpperCase(c)){
                if(checkerArray[1] == 0){
                    checkerArray[1]++;
                }
            }else if(Character.isDigit(c)){
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

        return simplicity < 2;
    }
}
