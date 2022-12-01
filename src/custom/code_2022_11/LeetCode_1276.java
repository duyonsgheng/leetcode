package custom.code_2022_11;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @ClassName LeetCode_1276
 * @Author Duys
 * @Description
 * @Date 2022/11/30 16:13
 **/
// 1276. 不浪费原料的汉堡制作方案
public class LeetCode_1276 {
    public List<Integer> numOfBurgers(int a, int b) {
        // 4a + 2b =a
        // a + b = b
        if (a - b * 2 < 0 || b * 4 - a < 0
                || (a - b * 2) % 2 != 0
                || (b * 4 - a) % 2 != 0) {
            return new ArrayList<>();
        }
        return Arrays.asList((a - b * 2) / 2, (b * 4 - a) / 2);
    }
}
