package custom.code_2023_06;

/**
 * @author Mr.Du
 * @ClassName LeetCode_1927
 * @date 2023年07月04日
 */
// 1927. 求和游戏
// https://leetcode.cn/problems/sum-game/
public class LeetCode_1927 {
    // Bob 获胜的条件是 num 中前一半数字的和 等于 后一半数字的和。Alice 获胜的条件是前一半的和与后一半的和 不相等
    // 如果问号的个数是奇数个，那么最后一个问号一定是Alice去操作，随便把问号改为任意数，Alice都赢了
    // 如果问号的个数是偶数个，
    public boolean sumGame(String num) {
        int n = num.length() / 2;
        int left_unknown = 0, right_unknown = 0, left_sum = 0, right_sum = 0;
        for (int i = 0; i < n; i++) {
            if (num.charAt(i) == '?') {
                left_unknown++;
            } else left_sum += num.charAt(i) - '0';

            if (num.charAt(i + n) == '?') {
                right_unknown++;
            } else right_sum += num.charAt(i + n) - '0';
        }
        int diff_unknown = left_unknown - right_unknown;
        int diff_sum = left_sum - right_sum;
        if (diff_sum * 2 == -9 * diff_unknown) {
            return false;
        }
        return true;
    }
}
