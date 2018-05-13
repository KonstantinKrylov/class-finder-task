import util.Utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static util.Utils.*;

public class ClassFinder {
    private static void runClassFinder(String[] args) throws IOException {

        List<String> classes = Files.readAllLines(Paths.get(args[0]))
                                    .stream()
                                    .map(String::trim)
                                    .collect(Collectors.toList());

        String pattern = args[1];

        StringBuilder result = new StringBuilder();

        if (pattern.equals("*")) {
            classes.stream()
                   .filter(c->!Objects.equals(c, ""))
                   .sorted(Utils::compareNames)
                   .forEach(c->result.append(c).append("\n"));
            System.out.print(result);
            return;
        }

        if (checkPattern(pattern)) {
            List<String> matched = classes
                    .stream()
                    .filter(s -> s.length() != 0)
                    .filter(s -> s.contains(".") ?
                            chooseSearchAndMatch(s.substring(s.lastIndexOf(".") + 1), pattern) :
                            chooseSearchAndMatch(s, pattern))
                    .sorted(Utils::compareNames)
                    .collect(Collectors.toList());

            matched.forEach(c->result.append(c).append("\n"));
            System.out.print(result);
        }
    }

    public static void main(String[] args) throws IOException {
        runClassFinder(args);
    }
}





