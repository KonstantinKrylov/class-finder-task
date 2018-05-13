import searchEngines.CamelCase;
import searchEngines.CaseInsensitive;
import searchEngines.SpaceEnded;
import searchEngines.WildCard;

import static util.Utilites.isCamelCasePattern;


public class InputChecker {
    public static boolean checkInput(String text, String pattern) {

        if (pattern.endsWith(" ")) {
            boolean isWildCard = pattern.contains("*");
            System.out.println("SPACE ENDED");
            return SpaceEnded.goMatch(text, pattern, isWildCard);
        }

        if (pattern.contains("*")) {
            System.out.println("WILD CARD");
            return WildCard.goMatch(text, pattern);
        }

        if (!isCamelCasePattern(pattern)) {
            System.out.println("CASE INSENSITIVE");
            return CaseInsensitive.goMatch(text.toLowerCase(), pattern);
        }


        System.out.println("CAMEL CASE");
        return CamelCase.goMatch(text, pattern);
    }

    public static void main(String[] args) {
        checkInput("GG", "ee");
    }
}
