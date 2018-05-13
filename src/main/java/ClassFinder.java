import util.Utilites;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static util.Utilites.*;

public class ClassFinder {
    public static void main(String[] args) throws IOException {

        List<String> classes = Files.readAllLines(Paths.get(args[0]))
                                    .stream()
                                    .map(String::trim)
                                    .collect(Collectors.toList());
        System.out.println(classes);

        String pattern = args[1];

        if (checkPattern(pattern)) {
            List<String> matched = classes
                    .stream()
                    .filter(s -> s.length() != 0)
                    .filter(s -> s.contains(".") ?
                            chooseSearchAndMatch(s.substring(s.lastIndexOf(".")+1),pattern) :
                            chooseSearchAndMatch(s,pattern))
                    .sorted(ClassFinder::compare)
                    .collect(Collectors.toList());
            System.out.println(matched);
        }
    }

    private static int compare(String a, String b) {
        return a.substring(a.lastIndexOf(".") + 1).compareTo(b.substring(b.lastIndexOf(".")+1));
    }
}



