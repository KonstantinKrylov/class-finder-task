package utilsTests;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static util.Utils.isCamelCasePattern;

@RunWith(Parameterized.class)
public class IsCamelCasePatternTest {

    private String pattern;
    private boolean expected;

    public IsCamelCasePatternTest(String pattern, boolean expected) {
        this.pattern = pattern;
        this.expected = expected;
    }

    @Parameterized.Parameters(name = "{index}: pattern = \"{0}\", expected = \"{1}\"")
    public static Iterable<Object[]> testData() {
        return Arrays.asList(new Object[][]{
                {"BeanFactoryPostProcessor", true},
                {"BFPP", true},
                {"beanfactorypostprocessor", false},
        });
    }

    @Test
    public void allStringSetsIdenticalTest() {
        assertEquals(expected, isCamelCasePattern(pattern));
    }
}
