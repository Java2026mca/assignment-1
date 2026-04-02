import java.io.*;

public class Grader {

    static String[][] TESTS = {
        { "10",  "2 3 5 7" },
        { "20",  "2 3 5 7 11 13 17 19" },
        { "2",   "2" },
        { "1",   "" },
        { "50",  "2 3 5 7 11 13 17 19 23 29 31 37 41 43 47" },
    };

    static String run(String input) throws Exception {
        ProcessBuilder pb = new ProcessBuilder("java", "-cp", "src/q06", "Main");
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
        System.out.println("=== Q06: Prime Numbers ===");
        for (int i = 0; i < TESTS.length; i++) {
            String actual   = run(TESTS[i][0]).replaceAll("\r\n","\n").strip();
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
