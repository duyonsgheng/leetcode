package duys_code.day_26;

import java.util.LinkedList;
import java.util.List;

/**
 * @ClassName Code_02_282_LeetCode
 * @Author Duys
 * @Description 力扣：https://leetcode-cn.com/problems/expression-add-operators/
 * @Date 2021/11/19 10:07
 **/
public class Code_02_282_LeetCode {

    public static List<String> addOperators(String num, int target) {
        //
        List<String> res = new LinkedList<>();
        if (num == null || num.length() == 0) {
            return res;
        }
        // 对num每一个数字的拷贝和两个数字之间 + - *的决定，沿途放到path里去
        char[] path = new char[num.length() * 2 - 1];
        // 原来的路径
        char[] digits = num.toCharArray();
        long n = 0;
        for (int i = 0; i < digits.length; i++) {
            // 尝试0到i作为前缀产生的所有
            n = n * 10 + digits[i] - '0';
            path[i] = digits[i];
            // 后续的过程来一个深度优先遍历
            dfs(res, path, i + 1, 0, n, digits, i + 1, target);
            if (n == 0) {
                break;
            }
        }
        return res;
    }

    // num 固定参数 就是num转成的char数组
    // target 目标
    // path 之前作过的决定，就是数字与数字之间可能是 + - * 和 空
    // left 已经固定的部分
    // cur 当前选择的时候，前一块的值
    // 默认市left + cur
    // len 就是我们的path已经涨到哪里了
    public static void dfs(List<String> res, char[] path, int len,
                           long left, long cur,
                           char[] num, int index, int target) {
        if (index == num.length) { // num都选择完了。没有数字可以选了
            if (left + cur == target) { // 我们之前固定的和当前位置的前一块的答案之和
                res.add(String.valueOf(path, 0, len));
                return;
            }
        }
        // 根据前面已经选了的，继续来一个深度优先遍历
        long n = 0;
        int j = len + 1;
        for (int i = index; i < num.length; i++) {
            // 尝试每一个可能的前缀
            // num[index..i]作为第一部分
            n = n * 10 + num[i] - '0';
            path[j++] = num[i];
            // 填一个+号
            path[len] = '+';
            // 选择是+号的话 我们的左半部分就可以结算了，下一个位置的前一部分就是n
            dfs(res, path, j, left + cur, n, num, i + 1, target);

            path[len] = '-';
            dfs(res, path, j, left + cur, -n, num, i + 1, target);

            path[len] = '*';
            dfs(res, path, j, left, cur * n, num, i + 1, target);
            // 如果前缀是0的话，选择不了了
            if (num[index] == '0') {
                break;
            }
        }
    }
}
