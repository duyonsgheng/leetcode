package custom.code_2022_10;

import java.util.Arrays;

/**
 * @ClassName LeetCode_948
 * @Author Duys
 * @Description
 * @Date 2022/10/19 14:56
 **/
// 948. 令牌放置
public class LeetCode_948 {
    public static void main(String[] args) {
        int[] t = {400};
        int p = 200;
        System.out.println(bagOfTokensScore(t, p));
    }

    public static int bagOfTokensScore(int[] tokens, int power) {
        Arrays.sort(tokens); // 排序
        int n = tokens.length;
        int l = 0;
        int r = n - 1;
        // 从左往右换积分
        // 从右往左换能量
        int cur = power;
        int cost = 0;
        while (l <= r) {
            // 换积分
            if (cur >= tokens[l] && l <= r) {
                cur -= tokens[l++];
                cost++;
            }
            // 换能量
            else if (cost > 0 && l < r) {
                cur += tokens[r--];
                cost--;
            } else break;
        }
        return cost;
    }
}
