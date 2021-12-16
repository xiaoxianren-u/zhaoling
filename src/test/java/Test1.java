import com.edu.dao.GrateFulDao;
import com.edu.pojo.GrateFul;
import com.edu.service.GrateFulService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.SimpleDateFormat;
import java.util.Date;
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

    @Autowired
    private GrateFulService grateFulService;
    @Autowired
    private GrateFulDao grateFulDao;

    @Test
    public void fun() {
        Date date = new Date();
        SimpleDateFormat dateFm = new SimpleDateFormat("EEEE");
        String currSun = dateFm.format(date);

        System.out.println(currSun);


    }

    @Test
    public void test() {
//        System.out.println("grateFulService.list(0,10,null,null) = " + grateFulService.list(0, 10,null,null));

        System.out.println("grateFulDao.count(new GrateFul(null,null,null,null,null)) = " + grateFulDao.count(new GrateFul(null, null, null, null, null)));
    }

}
