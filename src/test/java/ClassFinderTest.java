

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(Parameterized.class)
public class ClassFinderTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    private String[] args;
    private String expected;

    public ClassFinderTest(String[] args, String expected) {
        this.args = args;
        this.expected = expected;
    }

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @After
    public void restoreStreams() {
        System.setOut(System.out);
    }

    @Parameterized.Parameters(name = "{index}")
    public static Iterable<Object[]> testData() {
        return Arrays.asList(new Object[][]{
                {new String[]{"src/test/resources/classes.txt", "*"}, "c.d.FooBar\n" +
                        "a.b.FooBarBaz\n" +
                        "pakage.pack.ItWasNice\n" +
                        "pakage.pack.ItWasNiceTask\n" +
                        "codeborne.MindReader\n" +
                        "ScubaArgentineOperator\n" +
                        "TelephoneOperator\n" +
                        "codeborne.WishMaker\n" +
                        "YourEyesAreSpinningInTheirSockets\n" +
                        "YoureLeavingUsHere\n" +
                        "YouveComeToThisPoint\n"},
                {new String[]{"src/test/resources/classes.txt", "W*M*"}, "codeborne.WishMaker\n"},
                {new String[]{"src/test/resources/classes.txt", ""}, "Pattern is empty..."},
                {new String[]{"src/test/resources/classes.txt", " "}, "Pattern cannot start with space..."},
                {new String[]{"src/test/resources/classes.txt", "giveMeAll"}, "Specify valid class name pattern or use \"*\" as first symbol or instead spaces..."},
                {new String[]{"src/test/resources/classes.txt", "777"}, "Invalid pattern..."},
                {new String[]{"src/test/resources/classes.txt", "!@#$%"}, "Invalid pattern..."},
                {new String[]{"src/test/resources/classes.txt", "Foo0Bar"}, "Invalid pattern..."},
                {new String[]{"src/test/resources/classes.txt", "*operator"}, "ScubaArgentineOperator\n" + "TelephoneOperator\n"},
                {new String[]{"src/test/resources/classes.txt", "You*"}, "YourEyesAreSpinningInTheirSockets\n" + "YoureLeavingUsHere\n" + "YouveComeToThisPoint\n"},
                {new String[]{"src/test/resources/classes.txt", "re*ing"}, "YourEyesAreSpinningInTheirSockets\n" + "YoureLeavingUsHere\n"},
                {new String[]{"src/test/resources/classes.txt", "re*ing"}, "YourEyesAreSpinningInTheirSockets\n" + "YoureLeavingUsHere\n"},
                {new String[]{"src/test/resources/classes.txt", "S*Operator"}, "ScubaArgentineOperator\n"},
                {new String[]{"src/test/resources/classes.txt", "Nice "}, "pakage.pack.ItWasNice\n"},
        });
    }

    @Test
    public void allStringSetsIdenticalTest() throws IOException {
        ClassFinder.main(args);
        assertEquals(expected, outContent.toString());
    }
}
