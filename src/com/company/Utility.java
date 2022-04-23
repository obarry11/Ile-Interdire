package com.company;

public class Utility {

    public static int getRandomNumber(int min, int max)
    {
        return  (int)(Math.random() * ((max - min) + 1)) + min;
    }
}
