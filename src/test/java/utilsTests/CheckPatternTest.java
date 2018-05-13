package utilsTests;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static util.Utils.checkPattern;

@RunWith(Parameterized.class)
public class CheckPatternTest {

    private String pattern;
    private boolean expected;

    public CheckPatternTest(String pattern, boolean expected) {
        this.pattern = pattern;
        this.expected = expected;
    }

    @Parameterized.Parameters(name = "{index}: pattern = \"{0}\", expected = \"{1}\"")
    public static Iterable<Object[]> testData() {
        return Arrays.asList(new Object[][]{
                {"FBB", true},
                {"fbb", true},
                {"*F*B*B*", true},
                {"*fbb*", true},
                {"*fPb*", true},
                {"*fPb* ", true},
                {"fPb* ", false},
                {" Fbb ", false},
                {" ", false},
                {"", false},
                {"*", true},
                {"qwert78", false},
                {"777", false},
                {"!^&.", false},
                {".", false},
        });
    }

    @Test
    public void allStringSetsIdenticalTest() {
        assertEquals(expected, checkPattern(pattern));
    }
}
