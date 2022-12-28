package dev.restaurant.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utils {
    public static String generateUuid() {
        int length = 5;
        StringBuilder result = new StringBuilder(length);
        String characters =
                "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        int charactersLength = characters.length();
        for (int i = 0; i < length; i++) {
            result.append(characters.charAt((int) Math.floor(Math.random() * charactersLength)));
        }
        return result.toString();
    }
    public static boolean isContain(String source, String subItem){
        String pattern = "\\b"+subItem+"\\b";
        Pattern p=Pattern.compile(pattern);
        Matcher m=p.matcher(source);
        return m.find();
    }
}
