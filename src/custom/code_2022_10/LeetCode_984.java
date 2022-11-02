package custom.code_2022_10;

import java.util.LinkedList;
import java.util.List;

/**
 * @ClassName LeetCode_984
 * @Author Duys
 * @Description
 * @Date 2022/10/27 16:16
 **/
// 984. 不含 AAA 或 BBB 的字符串
public class LeetCode_984 {
    // 就轮流加
    // 谁多先加2个，然后交替进行
    public String strWithout3a3b(int a, int b) {
        StringBuffer sb = new StringBuffer();
        while (a != 0 || b != 0) {
            if (a > b) {
                int add = Math.min(2, a - b);
                a -= add;
                for (int i = 0; i < add; i++)
                    sb.append("a");
                if (add == 2 && b > 0) {
                    sb.append("b");
                    b--;
                }
            } else if (a < b) {
                int add = Math.min(2, b - a);
                b -= add;
                for (int i = 0; i < add; i++)
                    sb.append("b");
                if (add == 2 && a > 0) {
                    sb.append("a");
                    a--;
                }
            } else {
                sb.append("a").append("b");
                a--;
                b--;
            }
        }
        return sb.toString();
    }
}
