package custom.code_2022_10;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName LeetCode_1790
 * @Author Duys
 * @Description
 * @Date 2022/10/11 9:19
 **/
// 1790. 仅执行一次字符串交换能否使两个字符串相等
// 给你长度相等的两个字符串 s1 和 s2 。一次 字符串交换 操作的步骤如下：选出某个字符串中的两个下标（不必不同），并交换这两个下标所对应的字符。
public class LeetCode_1790 {

    public boolean areAlmostEqual(String s1, String s2) {
        // 统计一下
        int n = s1.length();
        // bank
        // kanb
        List<Integer> count = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            // 在此之前已经有两个位置了。没戏了
            if (s1.charAt(i) != s2.charAt(i)) {
                if (count.size() >= 2) {
                    return false;
                }
                // i位置不同
                count.add(i);
            }
        }
        // 一路都相等
        if (count.isEmpty()) {
            return true;
        }
        // 最多只能交换一次
        if (count.size() != 2) {
            return false;
        }
        return s1.charAt(count.get(0)) == s2.charAt(count.get(1))
                && s1.charAt(count.get(1)) == s2.charAt(count.get(0));
    }
}
