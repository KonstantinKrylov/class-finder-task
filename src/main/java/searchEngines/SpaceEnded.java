package searchEngines;

import java.util.List;

import static util.Utils.isCamelCasePattern;
import static util.Utils.splitByUpperCase;

public class SpaceEnded {
    public static boolean goMatch(String text, String pattern, boolean isWildCard) {

        if (!isWildCard) {

            if (!isCamelCasePattern(pattern)) return CaseInsensitive.goMatch(text.toLowerCase(), pattern.trim());

            List<String> textList = splitByUpperCase(text);
            List<String> patternList = splitByUpperCase(pattern.trim());

            return (textList.get(textList.size() - 1).equals(patternList.get(patternList.size() - 1))) &&
                    CamelCase.goMatch(text, pattern.trim());
        }

        return (!pattern.endsWith("* ") ||
                text.contains("" + pattern.charAt(pattern.lastIndexOf('*') - 1))) &&
                WildCard.goMatch(text, pattern.trim());
    }
}
