package searchEngines;

import java.util.List;

import static util.Utils.splitByUpperCase;

public class CamelCase {

    public static boolean goMatch(String text, String pattern) {

        List<String> textList = splitByUpperCase(text);
        List<String> patternList = splitByUpperCase(pattern);

        if (patternList.size() > textList.size()) return false;

        int k = 1;
        outer:
        for (int i = 1; i < patternList.size(); i++) {
            for (int j = k; j < textList.size(); j++) {
                if (textList.get(j).startsWith(patternList.get(i))) {
                    k += j;
                    continue outer;
                }
            }
            return false;
        }
        return true;
    }
}
