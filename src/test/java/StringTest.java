

import org.apache.commons.lang.StringUtils;
import org.junit.jupiter.api.Test;


/**
 * @author yz
 * @data: 2021/12/14 12:15 星期二
 * @file : StringTest.java
 */
public class StringTest {

    @Test
    public void fun() {

        String str = "12323";
        String str4 = "12 323";
        String str1 = "12323 ";
        String str2 = " 12323";
        String str3 = "       ";

        char[] a = str3.toCharArray();
        for (char s : a) {
            System.out.println("StringUtils.isEmpty(String.valueOf(s)) = " + StringUtils.isBlank(String.valueOf(s)));
            if (StringUtils.isBlank(String.valueOf(s))) {
                System.out.println("sssss");
            }
        }

//
//        System.out.println("\"\".equals(str) = " + "".equals(str));
//        System.out.println("\"\".equals(str1) = " + "".equals(str1));
//        System.out.println("\"\".equals(str2) = " + "".equals(str2));
//        System.out.println("\"\".equals(str3) = " + "".equals(str3));
//        System.out.println("str1.isEmpty() = " + str1.isEmpty());
//        System.out.println("str.isEmpty() = " + str.isEmpty());
//        System.out.println("StringUtils.isEmpty(str) = " + StringUtils.isEmpty(str));
//        System.out.println("StringUtils.isEmpty(str1) = " + StringUtils.isEmpty(str1));
//        System.out.println("StringUtils.isEmpty(str3) = " + StringUtils.isBlank(str2));
//        System.out.println("StringUtils.isEmpty(str3) = " + StringUtils.isBlank(str3));


    }
}
