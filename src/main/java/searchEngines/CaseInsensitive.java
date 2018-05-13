package searchEngines;

public class CaseInsensitive {

    public static boolean goMatch(String text, String pattern) {

        if (pattern.length() > text.length()) return false;

        int patternPoint = 0;
        int start = 0;
        int end = 1;

        String patternCut;
        StringBuilder patternUsed = new StringBuilder();

        while (true) {

            String currentPattern = pattern.substring(patternPoint);
            String currentText = text.substring(start, end);

            if (currentPattern.startsWith(currentText) && end != text.length()) {
                end++;
            } else {
                patternCut = (end == text.length() && currentPattern.equals(currentText)) ?
                        currentPattern :
                        currentPattern.substring(0, currentText.length() - 1);

                patternUsed.append(patternCut);
                patternPoint += patternCut.length();

                if (patternPoint >= pattern.length() || end == text.length()) break;

                start += patternCut.length() == 0 ? 1 : patternCut.length();
                end = start + 1;
            }
        }
        return patternUsed.toString().equals(pattern);
    }
}
