package searchEngines;

public class CaseInsensitive {

    public static boolean goMatch(String text, String pattern) {

     //   if (pattern.charAt(0) != text.charAt(0)) return false;
        if (pattern.length() > text.length()) return false;

        int patternPoint = 0;

        int text1 = 0;
        int text2 = 1;

        String patternCut;
        String patternUsed = "";

        while (text1 < 900) {

            String currentPattern = pattern.substring(patternPoint);
            String currentText = text.substring(text1, text2);

            if (currentPattern.startsWith(currentText) && text2 != text.length()) {
                text2++;
            } else {
                if (text2 == text.length() && currentPattern.equals(currentText)) patternCut = currentPattern;
                else patternCut = currentPattern.substring(0, currentText.length() - 1);

             //   System.out.println("PATTERN CUT " + patternCut);
                patternUsed += patternCut;

                patternPoint += patternCut.length();

                if (patternPoint >= pattern.length() || text2 == text.length()) break;

                if (patternCut.length() == 0) text1 += 1;
                else text1 += patternCut.length();

                text2 = text1 + 1;
            }

        }
        return patternUsed.equals(pattern);
    }

    public static void main(String[] args) {
        System.out.println(goMatch("foobarbazppp", "fbb"));
    }
}
