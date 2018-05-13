package searchEngines;

import java.util.List;
import java.util.stream.Collectors;

import static util.Utilites.*;

public class WildCard {
    public static boolean goMatch(String text, String pattern) {

        String asteriskLessPattern = splitByWildCard(pattern).stream()
                                                            .collect(Collectors.joining());

        return CaseInsensitive.goMatch(text,asteriskLessPattern);
    }

    public static void main(String[] args) {
        System.out.println(goMatch("KhcbajijCrdfr","K*a*C*"));
    }
}
