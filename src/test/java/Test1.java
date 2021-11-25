import org.junit.Test;

import java.util.Scanner;

/**
 * @author yz
 * @data: 2021/10/15 22:07 星期五
 * @file : Test.java
 */
public class Test1 {


    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        String t = input.next();


        int sum = 0;

        for (char s : t.toCharArray()) {
            if (s == 'A') {
                sum += 1;
            } else if (s == '1') {
                sum += 10;
            } else {
                sum += Character.getNumericValue(s);
            }


        }
        System.out.println(sum);

    }

    @Test
    public void fun() {
//        System.out.println("JwtUtils.generateToken(\"admin\") = " + JwtUtils.generateToken("admin"));


    }
}
