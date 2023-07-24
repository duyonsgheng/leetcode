package custom.code_2023_07;

/**
 * @author Mr.Du
 * @ClassName LeetCode_1963
 * @date 2023年07月17日
 */
// 1963. 使字符串平衡的最小交换次数
// https://leetcode.cn/problems/minimum-number-of-swaps-to-make-the-string-balanced/
public class LeetCode_1963 {
    public int minSwaps(String s) {
        int cnt = 0, mincnt = 0;
        for (char c : s.toCharArray()) {
            if (c == '[') {
                cnt++;
            } else {
                cnt--;
                mincnt = Math.min(mincnt, cnt);
            }
        }
        return (-mincnt + 1) / 2;
    }
}
