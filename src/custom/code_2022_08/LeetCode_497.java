package custom.code_2022_08;

import java.util.Random;

/**
 * @ClassName LeetCode_497
 * @Author Duys
 * @Description
 * @Date 2022/8/17 13:36
 **/
// 497. 非重叠矩形中的随机点
public class LeetCode_497 {

    class Solution {
        private int[][] arr;
        private int[] sum;
        private int n;
        Random random = new Random();

        public Solution(int[][] rects) {
            arr = rects;
            n = rects.length;
            sum = new int[n + 1];
            for (int i = 1; i <= n; i++) {
                sum[i] = sum[i - 1] + (arr[i - 1][2] - arr[i - 1][0] + 1) * (arr[i - 1][3] - arr[i - 1][1] + 1);
            }
        }

        public int[] pick() {
            // 这是面积
            int v = random.nextInt(sum[n]) + 1;
            int l = 0;
            int r = n;
            while (l < r) {
                int m = (l + r) >> 1;
                if (sum[m] >= v) {
                    r = m;
                } else {
                    l = m + 1;
                }
            }
            int[] cur = arr[r - 1];
            int x = random.nextInt(cur[2] - cur[0] + 1) + cur[0];
            int y = random.nextInt(cur[3] - cur[1] + 1) + cur[1];
            return new int[]{x, y};
        }
    }
}
