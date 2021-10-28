import com.edu.util.CodeUtils;
import org.junit.Test;

import javax.imageio.ImageIO;
import java.awt.image.RenderedImage;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Map;

/**
 * @author yz
 * @data: 2021/10/28 16:53 星期四
 * @file : CodeTest.java
 */
public class CodeTest {

    @Test
    public void fun() throws Exception {

        OutputStream out = new FileOutputStream("G://img/" + System.currentTimeMillis() + ".jpg");
        Map<String, Object> map = CodeUtils.generateCodeAndPic();
        ImageIO.write((RenderedImage) map.get("codePic"), "jpeg", out);
        System.out.println("验证码的值为：" + map.get("code"));
    }
}
