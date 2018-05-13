package searchEngines;

import java.util.List;

import static util.Utilites.splitByUpperCase;

public class SpaceEnded {
    public static boolean goMatch(String text, String pattern, boolean isWildCard) {

        if (!isWildCard) {
            List<String> textList = splitByUpperCase(text);
            List<String> patternList = splitByUpperCase(pattern.trim());

            if (!textList.get(textList.size() - 1).equals(patternList.get(patternList.size() - 1))) return false;

            return CamelCase.goMatch(text, pattern.trim());
        }

        if(pattern.endsWith("* ")){
            if(!text.contains(""+pattern.charAt(pattern.lastIndexOf('*')-1))) return false;
        }

        return WildCard.goMatch(text,pattern.trim());
    }

    public static void main(String[] args) {
        System.out.println(goMatch("QqqWwwEeevvte", "Q*Ee*e* ", true));
    }
}
