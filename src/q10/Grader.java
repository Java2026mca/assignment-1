import java.io.*;

public class Grader {

    static String[][] TESTS = {
        {
            "6\nPUSH 10\nPUSH 20\nPEEK\nPOP\nPOP\nPOP",
            "20\n20\n10\nEMPTY"
        },
        {
            "4\nPOP\nPUSH 5\nSIZE\nPOP",
            "EMPTY\n1\n5"
        },
        {
            "7\nPUSH 1\nPUSH 2\nPUSH 3\nSIZE\nPOP\nSIZE\nPEEK",
            "3\n3\n2\n2"
        },
    };

    static String run(String input) throws Exception {
        ProcessBuilder pb = new ProcessBuilder("java", "-cp", "src/q10", "Main");
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
        System.out.println("=== Q10: Stack Operations ===");
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
