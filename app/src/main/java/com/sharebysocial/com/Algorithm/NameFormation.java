package com.sharebysocial.com.Algorithm;

public class NameFormation {
    public static String getShortName(String name) {
        StringBuilder Name = new StringBuilder(name);
        StringBuilder sName = new StringBuilder();
        int i = 0;
        while (i < Name.length() && Name.charAt(i) != ' ') {
            sName.append(Name.charAt(i));
            i++;
        }
        if (i + 1 == Name.length()) {
            return sName.toString();
        }
        if (i <= Name.length()) {
            sName.append(Name.charAt(i));
            sName.append(Name.charAt(i + 1));
        }
        return sName.toString() + "...";
    }
}
