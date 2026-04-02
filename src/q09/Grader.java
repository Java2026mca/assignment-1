import java.io.*;

public class Grader {

    static String[][] TESTS = {
        {
            "2\n1 2\n3 4\n5 6\n7 8",
            "19 22\n43 50"
        },
        {
            "2\n1 0\n0 1\n4 5\n6 7",
            "4 5\n6 7"
        },
        {
            "3\n1 2 3\n4 5 6\n7 8 9\n9 8 7\n6 5 4\n3 2 1",
            "30 24 18\n84 69 54\n138 114 90"
        },
    };

    static String run(String input) throws Exception {
        ProcessBuilder pb = new ProcessBuilder("java", "-cp", "src/q09", "Main");
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
        System.out.println("=== Q09: Matrix Multiplication ===");
        for (int i = 0; i < TESTS.length; i++) {
            String actual   = run(TESTS[i][0]).replaceAll("\r\n", "\n").strip();
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
