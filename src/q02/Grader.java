import java.io.*;
import java.util.*;

public class Grader {

    static String[][] TESTS = {
        { "1", "1" },
        { "3", "1\n1 1\n1 2 1" },
        { "5", "1\n1 1\n1 2 1\n1 3 3 1\n1 4 6 4 1" },
        { "6", "1\n1 1\n1 2 1\n1 3 3 1\n1 4 6 4 1\n1 5 10 10 5 1" },
    };

    static String run(String input) throws Exception {
        ProcessBuilder pb = new ProcessBuilder("java", "-cp", "src/q02", "Main");
        pb.redirectErrorStream(true);
        Process p = pb.start();
        p.getOutputStream().write((input+"\n").getBytes());
        p.getOutputStream().flush();
        p.getOutputStream().close();
        String out = new String(p.getInputStream().readAllBytes()).stripTrailing();
        p.waitFor();
        return out;
    }

    public static void main(String[] args) throws Exception {
        int passed = 0;
        System.out.println("=== Q02: Pascal's Triangle ===");
        for (int i = 0; i < TESTS.length; i++) {
            String actual   = run(TESTS[i][0]).replaceAll("\r\n","\n").strip();
            String expected = TESTS[i][1].strip();
            boolean ok = actual.equals(expected);
            if (ok) { passed++; System.out.printf("  ✅ Test %d passed%n", i+1); }
            else {
                System.out.printf("  ❌ Test %d FAILED%n", i+1);
                System.out.println("     Expected:\n" + expected);
                System.out.println("     Got:\n" + actual);
            }
        }
        System.out.printf("  Score: %d/%d%n", passed, TESTS.length);
        if (passed < TESTS.length) System.exit(1);
    }
}
