package duys_code.day_28;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName Code_03_17_LeetCode
 * @Author Duys
 * @Description 力扣：https://leetcode-cn.com/problems/letter-combinations-of-a-phone-number/
 * @Date 2021/11/23 11:15
 **/
public class Code_03_17_LeetCode {

    public static char[][] phone = {
            {'a', 'b', 'c'}, // 2    0
            {'d', 'e', 'f'}, // 3    1
            {'g', 'h', 'i'}, // 4    2
            {'j', 'k', 'l'}, // 5    3
            {'m', 'n', 'o'}, // 6
            {'p', 'q', 'r', 's'}, // 7
            {'t', 'u', 'v'},   // 8
            {'w', 'x', 'y', 'z'}, // 9
    };

    public List<String> letterCombinations(String digits) {
        List<String> ans = new ArrayList<>();
        if (digits == null || digits.length() <= 0) {
            return ans;
        }
        char[] dist = digits.toCharArray();
        char[] path = new char[dist.length];
        process(dist, 0, path, ans);
        return ans;
    }

    public static void process(char[] dist, int index, char[] path, List<String> ans) {
        if (index == dist.length) {
            ans.add(new String(path));
        } else {
            // 当前的数字对应电话号上按键上有哪些字符
            char[] cands = phone[dist[index] - '2'];
            for (int i = 0; i < cands.length; i++) {
                path[index] = cands[i];
                process(dist, index + 1, path, ans);
            }
        }
    }
}
