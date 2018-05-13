package utilsTests;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.List;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static util.Utils.splitByWildCard;

@RunWith(Parameterized.class)
public class SplitByWildCardTest {

    private String pattern;
    private List<String> expected;

    public SplitByWildCardTest(String className, List<String> expected) {
        this.pattern = className;
        this.expected = expected;
    }
    @Parameterized.Parameters(name = "{index}: pattern = \"{0}\", expected = \"{1}\"")
    public static Iterable<Object[]> testData() {
        return Arrays.asList(new Object[][]{
                {"F*B", Arrays.asList("F","B")},
                {"*Fo*Ba*", Arrays.asList("Fo","Ba","")},
                {"Be*Fact*Post*roce*sor", Arrays.asList("Be","Fact","Post","roce","sor")},
        });
    }

    @Test
    public void allStringSetsIdenticalTest() {
        assertEquals(expected, splitByWildCard(pattern));
    }
}
