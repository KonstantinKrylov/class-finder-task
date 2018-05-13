package utilsTests;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.List;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static util.Utils.splitByUpperCase;

@RunWith(Parameterized.class)
public class SplitByUpperCaseTest {

    private String pattern;
    private List<String> expected;

    public SplitByUpperCaseTest(String className, List<String> expected) {
        this.pattern = className;
        this.expected = expected;
    }

    @Parameterized.Parameters(name = "{index}: pattern = \"{0}\", expected = \"{1}\"")
    public static Iterable<Object[]> testData() {
        return Arrays.asList(new Object[][]{
                {"BeanFactoryPostProcessor", Arrays.asList("Bean","Factory","Post","Processor")},
                {"BFPP", Arrays.asList("B","F","P","P")},
        });
    }

    @Test
    public void allStringSetsIdenticalTest() {
        assertEquals(expected, splitByUpperCase(pattern));
    }
}
