package com.maxiflexy.utils;

import java.time.Year;

public class AccountUtils {

    public static final String ACCOUNT_EXISTS_CODE = "001";

    public static final String ACCOUNT_EXISTS_MESSAGE = "This user already have an account created!";

    public static final String ACCOUNT_CREATION_SUCCESS_CODE = "002";

    public static final String ACCOUNT_CREATION_SUCCESS_MESSAGE = "Account has been created Successfully";

    public static final String ACCOUNT_NUMBER_NON_EXISTS_CODE = "003";

    public static final String ACCOUNT_NUMBER_NON_EXISTS_MESSAGE = "Provided account number does not exists";

    public static final String ACCOUNT_NUMBER_FOUND_CODE = "004";

    public static final String ACCOUNT_NUMBER_FOUND_MESSAGE = "Account number found";

    public static String generateAccountNumber(){

        //This algorithm will assume that your account number will be a total of 10 digits, since its default in Nigeria

        // 1. Get the current year
        Year currentYear = Year.now();

        // 2. Get six random digits
        int minimum = 100000;
        int maximum = 999999;

        // 3. Generate a random number between minimum and maximum
        int randomNumber = (int) Math.floor(Math.random() * (maximum - minimum + 1) + minimum);

        // 4. Convert current year and random six number to string and then concatenate
        String year = String.valueOf(currentYear);
        String randomNum = String.valueOf(randomNumber);

        // 5. Append both year and random num to generate the 10 digits account number
        StringBuilder accountNumber = new StringBuilder();

        return accountNumber.append(year).append(randomNum).toString();
    }
}
