import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class TextReader {

    public static void main(String[] args) throws IOException {
        System.out.println(countLine("src/QAtest.txt"));
        System.out.println(countA("src/QAtest.txt"));
    }

    private static long countLine(final String filePath) throws IOException {
        long start = System.currentTimeMillis();
        long result;
        try(Stream<String> lines = Files.lines(Paths.get(filePath))){
            result =  lines.count();
        }

        System.out.printf("Counting lines finished in %d second result = ",
                ((System.currentTimeMillis() - start)/1000));
        return result;
    }

    private static long countA(final String filePath) throws IOException {
        long start = System.currentTimeMillis();
        long result;
        try(Stream<String> lines = Files.lines(Paths.get(filePath))) {
            result = lines.parallel().flatMapToInt(CharSequence::chars).filter(c -> ('а'== c) || ('А' == c)).count();
        }

        System.out.printf("Counting the litters 'a', 'A' finished in %d second result = ",
                ((System.currentTimeMillis() - start)/1000));
        return result;
    }

}
