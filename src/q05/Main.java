import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String shape = sc.next();   // "circle", "rectangle", or "triangle"

        // TODO: Read dimensions based on shape type and print area rounded to 2 decimal places
        //
        // If shape == "circle"    → read radius (double)
        //                           area = π × r²
        // If shape == "rectangle" → read length width (2 doubles)
        //                           area = length × width
        // If shape == "triangle"  → read a b c (3 sides, doubles)
        //                           area = Heron's formula:
        //                           s = (a+b+c)/2, area = sqrt(s(s-a)(s-b)(s-c))
        //
        // Print: "Area: X.XX"  (exactly 2 decimal places)
        //
        // Sample Input:  circle 7
        // Sample Output: Area: 153.94
        //
        // Sample Input:  rectangle 4 6
        // Sample Output: Area: 24.00
        //
        // Sample Input:  triangle 3 4 5
        // Sample Output: Area: 6.00

    }
}
