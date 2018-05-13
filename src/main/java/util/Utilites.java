package util;

import java.util.ArrayList;
import java.util.List;

public class Utilites {
    public static List<String> splitByUpperCase(String input) {
        List<String> list = new ArrayList<>();
        String temp = "" + input.charAt(0);
        for (char c : input.substring(1).toCharArray()) {
            if (c >= 'A' && c <= 'Z') {
                list.add(temp);
                temp = "" + c;
                continue;
            }
            temp += "" + c;
        }
        list.add(temp);
        return list;
    }

    public static List<String> splitByWildCard(String input) {
        List<String> list = new ArrayList<>();
        String temp = "";
        for (char c : input.toCharArray()) {
            if (c == '*') {
                if (temp.length() != 0) list.add(temp);
                temp = c == '*' ? "" : "" + c;
                continue;
            }
            temp += "" + c;
        }
        list.add(temp);
        return list;
    }

    public static void main(String[] args) {
        System.out.println((splitByWildCard("*hhdGvv*bbvH")));
    }
}
