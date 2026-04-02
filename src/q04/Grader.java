import java.io.*;

public class Grader {

    static String[][] TESTS = {
        { "95",   "Grade: A\nResult: Pass" },
        { "82.5", "Grade: B\nResult: Pass" },
        { "60",   "Grade: C\nResult: Pass" },
        { "50",   "Grade: D\nResult: Pass" },
        { "30",   "Grade: F\nResult: Fail" },
        { "45",   "Grade: D\nResult: Pass" },
        { "90",   "Grade: A\nResult: Pass" },
    };

    static String run(String input) throws Exception {
        ProcessBuilder pb = new ProcessBuilder("java", "-cp", "src/q04", "Main");
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
        System.out.println("=== Q04: Student Grade ===");
        for (int i = 0; i < TESTS.length; i++) {
            String actual   = run(TESTS[i][0]).replaceAll("\r\n","\n").strip();
            String expected = TESTS[i][1].strip();
            boolean ok = actual.equals(expected);
            if (ok) { passed++; System.out.printf("  ✅ Test %d passed (marks=%s)%n", i+1, TESTS[i][0]); }
            else {
                System.out.printf("  ❌ Test %d FAILED (marks=%s)%n", i+1, TESTS[i][0]);
                System.out.println("     Expected: " + expected);
                System.out.println("     Got:      " + actual);
            }
        }
        System.out.printf("  Score: %d/%d%n", passed, TESTS.length);
        if (passed < TESTS.length) System.exit(1);
    }
}
