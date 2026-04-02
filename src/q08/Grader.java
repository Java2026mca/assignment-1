import java.io.*;

public class Grader {

    static String[][] TESTS = {
        { "1",  "0" },
        { "2",  "0 1" },
        { "5",  "0 1 1 2 3" },
        { "7",  "0 1 1 2 3 5 8" },
        { "10", "0 1 1 2 3 5 8 13 21 34" },
    };

    static String run(String input) throws Exception {
        ProcessBuilder pb = new ProcessBuilder("java", "-cp", "src/q08", "Main");
        pb.redirectErrorStream(true);
        Process p = pb.start();
        p.getOutputStream().write((input + "\n").getBytes());
        p.getOutputStream().flush();
        p.getOutputStream().close();
        String out = new String(p.getInputStream().readAllBytes()).stripTrailing();
        p.waitFor();
        return out;
    }

    public static void main(String[] args) throws Exception {
        int passed = 0;
        System.out.println("=== Q08: Fibonacci Series ===");
        for (int i = 0; i < TESTS.length; i++) {
            String actual   = run(TESTS[i][0]).replaceAll("\r\n", "\n").strip();
            String expected = TESTS[i][1].strip();
            boolean ok = actual.equals(expected);
            if (ok) { passed++; System.out.printf("  ✅ Test %d passed (n=%s)%n", i+1, TESTS[i][0]); }
            else {
                System.out.printf("  ❌ Test %d FAILED (n=%s)%n", i+1, TESTS[i][0]);
                System.out.println("     Expected: " + expected);
                System.out.println("     Got:      " + actual);
            }
        }
        System.out.printf("  Score: %d/%d%n", passed, TESTS.length);
        if (passed < TESTS.length) System.exit(1);
    }
}
