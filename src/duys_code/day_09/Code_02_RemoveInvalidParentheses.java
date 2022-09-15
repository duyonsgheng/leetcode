package duys_code.day_09;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName Code_02_RemoveInvalidParentheses
 * @Author Duys
 * @Description 力扣：https://leetcode-cn.com/problems/remove-invalid-parentheses/
 * @Date 2021/10/13 19:29
 **/
public class Code_02_RemoveInvalidParentheses {
    /**
     * 遇到 左括号 计数++
     * 遇到 右括号 计数--
     * 当计数 <0 了，说明需要干掉当前来到的位置，然后从i开始往后调用下一个过程5
     */

    public static List<String> removeInvalidParentheses(String s) {
        List<String> ans = new ArrayList<>();
        process(s, ans, 0, 0, new char[]{'(', ')'});
        return ans;
    }

    // modifyIndex <= checkIndex
    // 只查s[checkIndex....]的部分，因为之前的一定已经调整对了
    // 但是之前的部分是怎么调整对的，调整到了哪？就是modifyIndex
    // 比如：
    // ( ( ) ( ) ) ) ...
    // 0 1 2 3 4 5 6
    // 一开始当然checkIndex = 0，modifyIndex = 0
    // 当查到6的时候，发现不对了，
    // 然后可以去掉2位置、4位置的 )，都可以
    // 如果去掉2位置的 ), 那么下一步就是
    // ( ( ( ) ) ) ...
    // 0 1 2 3 4 5 6
    // checkIndex = 6 ，modifyIndex = 2
    // 如果去掉4位置的 ), 那么下一步就是
    // ( ( ) ( ) ) ...
    // 0 1 2 3 4 5 6
    // checkIndex = 6 ，modifyIndex = 4
    // 也就是说，
    // checkIndex和modifyIndex，分别表示查的开始 和 调的开始，之前的都不用管了  par  (  )
    public static void process(String s, List<String> ans, int checkIndex, int removeIndex, char[] par) {
        for (int count = 0, i = checkIndex; i < s.length(); i++) {
            if (s.charAt(i) == par[0]) {
                count++;
            }
            if (s.charAt(i) == par[1]) {
                count--;
            }
            // ‘）’ 比‘（’多了
            // 从这里删除
            if (count < 0) {
                for (int j = removeIndex; j <= i; ++j) {
                    if (s.charAt(j) == par[1] && (j == removeIndex || s.charAt(j - 1) != par[1])) {
                        process(s.substring(0, j) + s.substring(j + 1, s.length()), ans, i, j, par);
                    }
                }
                return;
            }
        }
        // 如果走到这里了，说明了 count>0,'('比')'多
        // 来一个反转
        // ((())(()) -> ))(())(((
        String res = new StringBuilder(s).reverse().toString();
        if (par[0] == '(') {
            process(res, ans, 0, 0, new char[]{')', '('});
        } else {
            ans.add(res);
        }
    }


}
