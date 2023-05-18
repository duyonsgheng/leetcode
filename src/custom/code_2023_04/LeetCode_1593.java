package custom.code_2023_04;

import java.util.HashSet;
import java.util.Set;

/**
 * @ClassName LeetCode_1593
 * @Author Duys
 * @Description
 * @Date 2023/4/17 16:53
 **/
// 1593. 拆分字符串使唯一子字符串的数目最大
public class LeetCode_1593 {
    int max = 1;

    public int maxUniqueSplit(String s) {
        Set<String> set = new HashSet<>();
        process(0, 0, s, set);
        return max;
    }

    public void process(int index, int split, String s, Set<String> set) {
        int len = s.length();
        if (index >= len) {
            max = Math.max(max, split);
        } else {
            for (int i = index; i < len; i++) {
                String cur = s.substring(index, i + 1);
                if (set.add(cur)) {
                    process(i + 1, split + 1, s, set);
                    set.remove(cur);
                }
            }
        }
    }
}
