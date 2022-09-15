package custom.code_2022_07;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName LeetCode_241
 * @Author Duys
 * @Description
 * @Date 2022/7/13 14:35
 **/
// 241. 为运算表达式设计优先级
public class LeetCode_241 {

    public List<Integer> diffWaysToCompute(String expression) {
        List<Integer> ans = new ArrayList<>();
        int len = expression.length();
        int start = 0;
        // 找到第一个符号位置
        for (start = 0; start < len; start++) {
            if (Character.isDigit(expression.charAt(start))) {
                continue;
            } else break;
        }
        if (start == len) {
            ans.add(Integer.parseInt(expression));
        }
        // 从符号位置开始
        for (int i = start; i < len; i++) {
            if (Character.isDigit(expression.charAt(i))) {
                continue;
            }
            // 找到符号位置，根据当前符号位置进行划分
            char op = expression.charAt(i);
            // 左边的答案
            List<Integer> left = diffWaysToCompute(expression.substring(0, i));
            // 右边的答案
            List<Integer> right = diffWaysToCompute(expression.substring(i + 1, len));

            // 左右结合
            for (int l : left) {
                for (int r : right) {
                    if (op == '+') {
                        ans.add(l + r);
                    } else if (op == '-') {
                        ans.add(l - r);
                    } else if (op == '*') {
                        ans.add(l * r);
                    }
                }
            }
        }
        return ans;
    }
}
