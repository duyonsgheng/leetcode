package custom.code_2022_11;

/**
 * @ClassName LeetCode_1007
 * @Author Duys
 * @Description
 * @Date 2022/11/2 15:16
 **/
// 1007. 行相等的最少多米诺旋转
public class LeetCode_1007 {
    public int minDominoRotations(int[] tops, int[] bottoms) {
        int ans = Integer.MAX_VALUE;
        int n = tops.length;
        for (int i = 1; i <= 6; i++) {
            int a = 0;
            int b = 0;
            int j = 0;
            // 其中一个相等就可以尝试交换
            while (j < n && (tops[j] == i || bottoms[j] == i)) {
                // 如果tops种的不等，那么一定bottoms种是等于的
                if (tops[j] != i) {
                    a++;
                }
                // 那么一定bottoms种是不等于的
                if (bottoms[j] != i) {
                    b++;
                }
                j++;
            }
            if (j == n) {
                ans = Math.min(ans, Math.min(a, b));
            }
        }
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }
}
