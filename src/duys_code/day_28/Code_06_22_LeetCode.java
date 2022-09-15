package duys_code.day_28;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName Code_06_22_LeetCode
 * @Author Duys
 * @Description 力扣：https://leetcode-cn.com/problems/generate-parentheses/
 * @Date 2021/11/23 15:17
 **/
public class Code_06_22_LeetCode {

    // n表示括号的对数
    public List<String> generateParenthesis(int n) {
        char[] path = new char[n << 1];
        List<String> ans = new ArrayList<>();
        process(path, 0, 0, n, ans);
        return ans;
    }

    // path 之前做的决定
    // index 当前来到哪个位置做决定了，因为 path的长度是固定的
    // leftMinusRight 表示已经作过的决定里面 左括号 - 右括号的余数
    // leftRest 左括号还剩下几个
    public static void process(char[] path, int index, int leftMinusRight, int leftRest, List<String> ans) {
        if (index == path.length) {
            ans.add(new String(path));
        } else {
            // 左括号还有剩余的，才能继续左括号
            if (leftRest > 0) {
                path[index] = '(';
                // 当前添加了一个左括号
                // 那么左括号减去一个
                // 左括号-右括号的数+1
                process(path, index + 1, leftMinusRight + 1, leftRest - 1, ans);
            }
            // 说明左括号比右括号多了
            if (leftMinusRight > 0) {
                path[index] = ')';
                process(path, index + 1, leftMinusRight - 1, leftRest, ans);
            }
        }
    }
}
