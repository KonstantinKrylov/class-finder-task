package util;

import searchEngines.CamelCase;
import searchEngines.CaseInsensitive;
import searchEngines.SpaceEnded;
import searchEngines.WildCard;

import java.util.ArrayList;
import java.util.List;

public class Utils {
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
        StringBuilder temp = new StringBuilder();
        for (char c : input.toCharArray()) {
            if (c == '*') {
                if (temp.length() != 0) list.add(temp.toString());
                temp = new StringBuilder();
                continue;
            }
            temp.append("").append(c);
        }
        list.add(temp.toString());
        return list;
    }

    public static boolean isCamelCasePattern(String pattern) {
        int count = 0;
        for (char c : pattern.trim().toCharArray()) {
            if (c == '*') continue;
            if (c < 'Z') count++;
        }
        return count != 0;
    }

    public static boolean chooseSearchAndMatch(String text, String pattern) {

        if (pattern.endsWith(" ")) {
            boolean isWildCard = pattern.contains("*");
            return SpaceEnded.goMatch(text, pattern, isWildCard);
        }

        if (pattern.contains("*")) {
            return WildCard.goMatch(text, pattern);
        }

        if (!isCamelCasePattern(pattern)) {
            return CaseInsensitive.goMatch(text.toLowerCase(), pattern);
        }

        return CamelCase.goMatch(text, pattern);
    }

    public static boolean checkPattern(String pattern) {

        if (pattern.length() == 0) {
            System.out.print("Pattern is empty...");
            return false;
        }

        if (pattern.startsWith(" ")) {
            System.out.print("Pattern cannot start with space...");
            return false;
        }

        for (char c : pattern.toCharArray()) {
            if (c < 'A' || c > 'z' || (c > 'Z' && c < 'a') || pattern.contains("**") || pattern.contains("  ")) {
                if (c != ' ' && c != '*') {
                    System.out.print("Invalid pattern...");
                    return false;
                }
            }
        }
        
        char patternStart = pattern.charAt(0);
        if (patternStart > 'Z' && isCamelCasePattern(pattern)) {
            System.out.print("Specify valid class name pattern or use \"*\" as first symbol or instead spaces...");
            return false;
        }
        return true;
    }

    public static int compareNames(String a, String b) {
        return a.substring(a.lastIndexOf(".") + 1).compareTo(b.substring(b.lastIndexOf(".")+1));
    }
}
