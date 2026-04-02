import java.io.*;

public class Grader {

    static String[][] TESTS = {
        { "circle 7",       "Area: 153.94" },
        { "circle 1",       "Area: 3.14"   },
        { "rectangle 4 6",  "Area: 24.00"  },
        { "rectangle 10 5", "Area: 50.00"  },
        { "triangle 3 4 5", "Area: 6.00"   },
        { "triangle 5 5 5", "Area: 10.83"  },
    };

    static String run(String input) throws Exception {
        ProcessBuilder pb = new ProcessBuilder("java", "-cp", "src/q05", "Main");
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
        System.out.println("=== Q05: Shape Area ===");
        for (int i = 0; i < TESTS.length; i++) {
            String actual   = run(TESTS[i][0]).replaceAll("\r\n","\n").strip();
            String expected = TESTS[i][1].strip();
            boolean ok = actual.equals(expected);
            if (ok) { passed++; System.out.printf("  ✅ Test %d passed%n", i+1); }
            else {
                System.out.printf("  ❌ Test %d FAILED (input: %s)%n", i+1, TESTS[i][0]);
                System.out.println("     Expected: " + expected);
                System.out.println("     Got:      " + actual);
            }
        }
        System.out.printf("  Score: %d/%d%n", passed, TESTS.length);
        if (passed < TESTS.length) System.exit(1);
    }
}
