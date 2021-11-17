package com.virtualSlime.Utils;

import java.util.Random;

public class NumberProcessing {
    private static final Random random = new Random();

    /**
     * Function for generating a String-typed 4-digit number
     * @param seed - a long number from timestamp
     * @return generated 4-digit number
     */
    public static String getRandomFourDigitNumber(long seed){
        random.setSeed(seed);

        int newNum = random.nextInt(1,10000);
        StringBuilder code = new StringBuilder(Integer.toString(newNum));
        while(newNum <= 10000){
            newNum *= 10;
            if(newNum >= 10000){
                break;
            }else{
                code.insert(0, "0");
            }
        }

        return code.toString();
    }
}
