import com.edu.util.MD5Util;
import org.junit.Test;

/**
 * @author yz
 * @data: 2021/10/24 13:27 星期日
 * @file : MD5Test.java
 */
public class MD5Test {

    @Test
    public void fun() {
        String yan = "!a@n%p&";
        System.out.println("md5(\"admin123\") = " + MD5Util.md5(yan + "123" + yan));
        System.out.println("inputPassToFromPass(md5(\"admin123\")) = " + MD5Util.inputPassToFromPass("86057bd852cc58a3fd1b3feee5eaa183"));
    }
}
