package custom.code_2023_02;

import java.util.Arrays;

/**
 * @ClassName LeetCode_1561
 * @Author Duys
 * @Description
 * @Date 2023/2/14 9:54
 **/
// 1561. 你可以获得的最大硬币数目
public class LeetCode_1561 {
    // 三个一组，每次拿走第二大的数
    public int maxCoins(int[] piles) {
        int n = piles.length;
        int ans = 0;
        // 从i开始取，总共有j堆
        // 9,8,7,6,5,1,2,3,4
        // 123 456 789
        Arrays.sort(piles);
        for (int i = n - 2, j = 0; j < n / 3; i -= 2, j++) {
            ans += piles[i];
        }
        return ans;
    }
}
