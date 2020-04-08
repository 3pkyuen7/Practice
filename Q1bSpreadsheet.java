import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
// implementation, math
public class Q1bSpreadsheet {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int time = Integer.parseInt(scanner.nextLine());// if input 1bc, exception
        for (int i = 0; i < time; i++) {
            String input = scanner.nextLine();
            // System.out.println("input : " + input);
            String[] tmp;
            if (input.matches("^[R]\\d+[C]\\d+$")) { // [R] and [RC] means character R and (R or C) : R23C55
                tmp = input.split("\\D");   // \D : non-digit : [0](row) -> 23, [1](col) -> 55
                int c = Integer.parseInt(tmp[2]); // 55-> 2*26^1 + 3*26^0 = 55(BC) | 52-> 1*26^1 + 26*26^0 = 52(AZ)
                System.out.println(autoIncrement(c) + tmp[1]);
            } else {
                tmp = input.split("(?<=\\D)(?=\\d)");
                String columnString = tmp[0];
                String rowString = tmp[1];
                System.out.print("R" + rowString);
                System.out.print("C"); // about col
                int column = 0;
                // System.out.println("colStr length : " + columnString.length());
                for (int k = 0; k < columnString.length(); k++) {
                    // System.out.println(columnString.length() - (k + 1));
                    int number = toInt(columnString.charAt(k)); // BC -> B = 2, C = 3
                    column += number * Math.pow(26, columnString.length() - (k + 1)); // length is 2, k -> 1,0
                    //  System.out.println("number : "+number+" k : "+k);
                }
                // System.out.println("testing A = "+toInt('A'));
                System.out.print(column);
                System.out.println();
            }
        }
    }

    public static String autoIncrement(int index) { // 1 -> A, 26 -> Z, 27-> AA, 0->A, 26-> AA, 27->AB
        int power = (int) Math.floor(Math.log(index) / Math.log(26));
        //StringBuilder output = new StringBuilder("Column Index : "); // 27(AA) = 1*26^1+1*26^0;
        StringBuilder output;
        int tmp = index;    // p = power, tmp = ,
        //System.out.println("power : " + power);
        char[] coefficients = new char[power+1];
        for (int i = power; i >= 0; i--) {
            int coefficient = (tmp / (int) (Math.pow(26, i)));
            coefficients[power-i] = toChar(coefficient);
            tmp = tmp % ((int) Math.pow(26, i));
        }
        for (int j = coefficients.length - 1; j >= 1; j--) {
            if (toInt(coefficients[j]) <= 0) {
                coefficients[j] = toChar(toInt(coefficients[j]) + 26);
                coefficients[j - 1] = toChar(toInt(coefficients[j-1]) - 1);
            }
        }
        output = new StringBuilder(String.valueOf(coefficients));
        if(output.charAt(0)=='@'){
            output.deleteCharAt(0);
        }
        return output.toString();
    }

    public static int toInt(char c) {
        return ((int) c - 64);
    }

    public static char toChar(int i) {
        return (char) (i + 64);
    }
}
