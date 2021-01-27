package com.example.numverifyapi.Data;

public class Validation {

    /**
     * validate method to check any number hasn't characters
     * return true if valid
     * @param phone
     * @return boolean
     */

    public static boolean validate(String phone) {

        for (int i = 0; i < phone.length(); i++) {
            Boolean flag = Character.isDigit(phone.charAt(i));
            if (flag) { continue; }
            else { return false; }
        }
        return true;
    }
}
