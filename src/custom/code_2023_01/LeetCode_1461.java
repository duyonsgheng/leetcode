package custom.code_2023_01;

import java.util.HashSet;
import java.util.Set;

/**
 * @ClassName LeetCode_1461
 * @Author Duys
 * @Description
 * @Date 2023/1/17 14:21
 **/
// 1461. 检查一个字符串是否包含所有长度为 K 的二进制子串
public class LeetCode_1461 {

    public boolean hasAllCodes(String s, int k) {
        if (s.length() < (1 << k) + k - 1) {
            return false;
        }
        Set<String> set = new HashSet<>();
        for (int i = 0; i + k <= s.length(); i++) {
            set.add(s.substring(i, i + k));
        }
        return set.size() == (1 << k);
    }
}
