package custom.code_2023_04;

import java.util.Arrays;

/**
 * @ClassName LeetCode_1641
 * @Author Duys
 * @Description
 * @Date 2023/4/21 15:20
 **/
// 1641. 统计字典序元音字符串的数目
public class LeetCode_1641 {
    public int countVowelStrings(int n) {
        int[] dp = new int[5];
        Arrays.fill(dp, 1);
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < 5; j++) {
                dp[j] += dp[j - 1];
            }
        }
        return Arrays.stream(dp).sum();
    }
}
