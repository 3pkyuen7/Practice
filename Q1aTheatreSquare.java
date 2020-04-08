import java.util.*;
// math
public class Q1aTheatreSquare {
    public static void main(String[] args){ // sample input 6 6 4
        Scanner scanner = new Scanner(System.in);
        double firstInput = scanner.nextDouble();
        double secInput = scanner.nextDouble();
        double thirdInput = scanner.nextDouble();
        System.out.println((long)(Math.ceil(firstInput/thirdInput)*Math.ceil(secInput/thirdInput))); // no int, overflow
    } // sample output 4
}
