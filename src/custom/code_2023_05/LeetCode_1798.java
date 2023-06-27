package custom.code_2023_05;

import java.util.Arrays;

/**
 * @ClassName LeetCode_1798
 * @Author Duys
 * @Description
 * @Date 2023/5/22 13:43
 **/
// 1798. 你能构造出连续值的最大数目
public class LeetCode_1798 {
    public int getMaximumConsecutive(int[] coins) {
        int ans = 1; // 一个也不取就是0；表示已经存在一个了
        Arrays.sort(coins);
        for (int c : coins) {
            if (c > ans) { // 如果存在断档的数字，就表示不行了
                break;
            }
            ans += c;
        }
        return ans;
    }
}
