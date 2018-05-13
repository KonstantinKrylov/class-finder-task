package searchEnginesTests;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static searchEngines.WildCard.goMatch;


@RunWith(Parameterized.class)
public class WildCardTest {

    private String text;
    private String pattern;
    private boolean expected;

    public WildCardTest(String text, String pattern, boolean expected) {
        this.text = text;
        this.pattern = pattern;
        this.expected = expected;
    }

    @Parameterized.Parameters(name = "{index}: text = \"{0}\", pattern = \"{1}\", expected = \"{2}\"")
    public static Iterable<Object[]> testData() {
        return Arrays.asList(new Object[][]{
                {"FooBarBaz", "B*rBaz", true},
                {"FooBrBaz", "B*rBaz", false},
                {"FooBrBaz", "*B*z", true},
                {"BrBaz", "*B*z", false},
                {"BrBaz", "*B*z", false},
                {"BeanFactoryPostProcessor", "BFPP", false},
                {"BeanFactoryPostProcessor", "B*F*P*P", true},
                {"BeanFactoryPostProcessor", "b*f*p*p", true},
                {"BeanFactoryPostProcessor", "*ea*ct*st*ss*", true},
                {"BeanBeanb", "b*", false},
                {"BeanBeanbBean", "b*", true},

        });
    }

    @Test
    public void allStringSetsIdenticalTest() {
        assertEquals(expected, goMatch(text, pattern));
    }
}

