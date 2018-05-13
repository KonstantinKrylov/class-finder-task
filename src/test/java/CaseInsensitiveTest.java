import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static searchEngines.CaseInsensitive.*;


@RunWith(Parameterized.class)
public class CaseInsensitiveTest {

    private String text;
    private String pattern;
    private boolean expected;

    public CaseInsensitiveTest(String text, String pattern, boolean expected) {
        this.text = text;
        this.pattern = pattern;
        this.expected = expected;
    }

    @Parameterized.Parameters(name = "{index}: text = \"{0}\", pattern = \"{1}\", expected = \"{2}\"")
    public static Iterable<Object[]> testData() {
        return Arrays.asList(new Object[][]{
                {"foobarbaz", "fbb", true},
                {"foobarbaz", "fobaba", true},
                {"foobarbaz", "foobarbaz", true},
                {"foobarbaz", "f", true},
                {"foobarbaz", "z", true},
                {"foobarbaz", "fz", true},
                {"foobarbaz", "ffzz", false},
                {"abcdefj", "acej", true},
        });
    }

    @Test
    public void allStringSetsIdenticalTest() {
        assertEquals(expected, goMatch(text, pattern));
    }
}

