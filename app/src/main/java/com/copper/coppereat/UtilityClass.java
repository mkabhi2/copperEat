package com.copper.coppereat;

/**
 * Created by vijaysingh on 10/25/2017.
 */

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

/**
 * Created by vijaysingh on 10/24/2017.
 */

public class UtilityClass {

    public static boolean validate(String email){
        boolean result=true;
        try {
            InternetAddress emailAddr = new InternetAddress(email);
            emailAddr.validate();
        } catch (AddressException ex) {
            result=false;
        }
        return result;
    }
}
