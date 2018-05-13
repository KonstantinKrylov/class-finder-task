package searchEngines;

import java.util.List;

import static util.Utilites.splitByUpperCase;

public class CamelCase {

    public static boolean goMatch(String text, String pattern) {

        List<String> textList = splitByUpperCase(text);
        List<String> patternList = splitByUpperCase(pattern);

        if (patternList.size() > textList.size() || !textList.get(0).startsWith(patternList.get(0))) return false;

        int k = 1;
        outer:
        for (int i = 1; i < patternList.size(); i++) {

            for (int j = k; j < textList.size(); j++) {


                String textP = textList.get(j);
                String patternP = patternList.get(i);

                if (textList.get(j).startsWith(patternList.get(i))) {
                    k += j;
                    continue outer;
                }
            }
            return false;
        }
        System.out.println();
        return true;
    }

    public static void main(String[] args) {
        System.out.println(goMatch("QqqWwwEee", "QWEee"));
    }
}
