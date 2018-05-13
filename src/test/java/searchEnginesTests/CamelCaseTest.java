package searchEnginesTests;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static searchEngines.CamelCase.goMatch;


@RunWith(Parameterized.class)
public class CamelCaseTest {

    private String text;
    private String pattern;
    private boolean expected;

    public CamelCaseTest(String text, String pattern, boolean expected) {
        this.text = text;
        this.pattern = pattern;
        this.expected = expected;
    }

    @Parameterized.Parameters(name = "{index}: text = \"{0}\", pattern = \"{1}\", expected = \"{2}\"")
    public static Iterable<Object[]> testData() {
        return Arrays.asList(new Object[][]{
                {"FooBarBaz", "FB", true},
                {"FooBarBaz", "FoBa", true},
                {"FooBarBaz", "FBar", true},
                {"FooBar", "FB", true},
                {"FooBar", "FoBa", true},
                {"FooBar", "FBar", true},
        });
    }

    @Test
    public void allStringSetsIdenticalTest() {
        assertEquals(expected, goMatch(text, pattern));
    }
}

