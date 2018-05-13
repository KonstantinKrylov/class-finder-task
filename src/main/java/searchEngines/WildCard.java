package searchEngines;

import java.util.List;
import java.util.stream.Collectors;

import static util.Utils.*;

public class WildCard {
    public static boolean goMatch(String className, String pattern) {

        String text = isCamelCasePattern(pattern) ? className : className.toLowerCase();

        if (pattern.charAt(0) == '*' && pattern.charAt(1) == text.charAt(0)) return false;
        if (pattern.endsWith("*") && pattern.charAt(pattern.lastIndexOf("*") - 1) == text.charAt(text.length() - 1))
            return false;

        List<String> list = splitByWildCard(pattern);

        int textJoint = Integer.MIN_VALUE;
        int start = 0;

        StringBuilder patternUsed = new StringBuilder();

        String asteriskLessPattern = splitByWildCard(pattern).stream()
                                                             .collect(Collectors.joining());
        for (int i = 0; i < list.size(); i++) {

            String currentText = text.substring(start);

            if (!currentText.contains(list.get(i))) {
                return false;
            } else {
                if (currentText.indexOf(list.get(i)) != textJoint) {
                    textJoint = currentText.indexOf(list.get(i)) + list.get(i).length() * 2+1;
                    start = textJoint-1;
                    patternUsed.append(list.get(i));
                    if (patternUsed.toString().equals(asteriskLessPattern)) break;
                } else {
                    i--;
                }
            }
        }
        return patternUsed.toString().equals(asteriskLessPattern);
    }
}
