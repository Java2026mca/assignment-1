import java.io.*;

public class Grader {

    static String[][] TESTS = {
        { "1", "1\nDiagonal: 1" },
        { "2", "1 2\n4 3\nDiagonal: 4" },
        { "3", "1 2 3\n8 9 4\n7 6 5\nDiagonal: 15" },
        { "4", "1 2 3 4\n12 13 14 5\n11 16 15 6\n10 9 8 7\nDiagonal: 46" },
    };

    static String run(String input) throws Exception {
        ProcessBuilder pb = new ProcessBuilder("java", "-cp", "src/q03", "Main");
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
        System.out.println("=== Q03: Spiral Matrix ===");
        for (int i = 0; i < TESTS.length; i++) {
            String actual   = run(TESTS[i][0]).replaceAll("\r\n","\n").strip();
            String expected = TESTS[i][1].strip();
            boolean ok = actual.equals(expected);
            if (ok) { passed++; System.out.printf("  ✅ Test %d passed (n=%s)%n", i+1, TESTS[i][0]); }
            else {
                System.out.printf("  ❌ Test %d FAILED (n=%s)%n", i+1, TESTS[i][0]);
                System.out.println("     Expected:\n" + expected);
                System.out.println("     Got:\n" + actual);
            }
        }
        System.out.printf("  Score: %d/%d%n", passed, TESTS.length);
        if (passed < TESTS.length) System.exit(1);
    }
}
