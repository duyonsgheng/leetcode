package custom.code_2022_09;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName LeetCode_828
 * @Author Duys
 * @Description
 * @Date 2022/9/6 9:41
 **/
// 828. 统计子串中的唯一字符
public class LeetCode_828 {
    // 1. 对于下标为 i 的字符 c[i]，当它在某个子字符串中仅出现一次时，它会对这个子字符串统计唯一字符时有贡献。
    // 只需对每个字符，计算有多少子字符串仅包含该字符一次即可
    // 2.求这个字符可能会带来的影响，当前位置到开始，下一个位置到当前位置 他们所有的组合
    public int uniqueLetterString(String s) {
        Map<Character, List<Integer>> indexMap = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            char cur = s.charAt(i);
            if (!indexMap.containsKey(cur)) {
                indexMap.put(cur, new ArrayList<>());
                indexMap.get(cur).add(-1); // 给每一个字符串一个初始位置
            }
            indexMap.get(cur).add(i);
        }
        int res = 0;
        for (Character c : indexMap.keySet()) {
            List<Integer> cur = indexMap.get(c);
            cur.add(s.length());
            for (int i = 1; i < cur.size() - 1; i++) {
                res += (cur.get(i) - cur.get(i - 1)) * (cur.get(i + 1) - cur.get(i));
            }
        }
        return res;
    }
}
