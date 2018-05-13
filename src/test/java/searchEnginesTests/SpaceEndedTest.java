package searchEnginesTests;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

import static searchEngines.SpaceEnded.*;

@RunWith(Parameterized.class)
public class SpaceEndedTest {

    private String text;
    private String pattern;
    private boolean isWildCard;
    private boolean expected;

    public SpaceEndedTest(String text, String pattern, boolean isWildCard, boolean expected) {
        this.text = text;
        this.pattern = pattern;
        this.isWildCard = isWildCard;
        this.expected = expected;
    }

    @Parameterized.Parameters(name = "{index}: text = \"{0}\", pattern = \"{1}\", isWildCard = \"{2}\", expected = \"{3}\"")
    public static Iterable<Object[]> testData() {
        return Arrays.asList(new Object[][]{
                {"FooBar", "FBar ", false, true},
                {"foobar", "fbar ", false, true},
                {"foobar", "f*b*r ", true, true},
                {"foobar", "f*b*r ", false, false},
                {"FillThisGap", "F*Th*Gap ", true, true},
                {"FillThisGap", "F*Th*Ga* ", true, true},


        });
    }

    @Test
    public void allStringSetsIdenticalTest() {
        assertEquals(expected, goMatch(text, pattern, isWildCard));
    }
}
