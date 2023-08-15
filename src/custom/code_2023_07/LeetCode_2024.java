package custom.code_2023_07;

/**
 * @author Mr.Du
 * @ClassName LeetCode_2024
 * @date 2023年07月31日
 */
// 2024. 考试的最大困扰度
// https://leetcode.cn/problems/maximize-the-confusion-of-an-exam/
public class LeetCode_2024 {
    // 窗口
    public int maxConsecutiveAnswers(String answerKey, int k) {
        return Math.max(max(answerKey, k, 'F'), max(answerKey, k, 'T'));
    }

    public int max(String str, int k, char c) {
        int n = str.length();
        int ans = 0;
        for (int left = 0, right = 0, sum = 0; right < n; right++) {
            sum += str.charAt(right) != c ? 1 : 0; // 当前窗口内有几个不是c的
            // 看看k能消除几个不是c的
            while (sum > k) {
                sum -= str.charAt(left++) != c ? 1 : 0;
            }
            // 剩余的全部是c
            ans = Math.max(ans, right - left + 1);
        }
        return ans;
    }
}
