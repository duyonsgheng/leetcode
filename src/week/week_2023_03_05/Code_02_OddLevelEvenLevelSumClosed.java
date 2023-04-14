package week.week_2023_03_05;

/**
 * @ClassName Code_02_OddLevelEvenLevelSumClosed
 * @Author Duys
 * @Description
 * @Date 2023/3/30 9:16
 **/
// 来自腾讯音乐
// 给定一棵树，一共有n个点
// 每个点上没有值，请把1~n这些数字，不重复的分配到二叉树上
// 做到 : 奇数层节点的值总和 与 偶数层节点的值总和 相差不超过1
// 返回奇数层节点分配值的一个方案
// 2 <= n <= 10^5
public class Code_02_OddLevelEvenLevelSumClosed {
    // 现根据题意把树中的奇数层节点或者偶数层节点记录
    // 然后1到n的等差数列求和公式，算出和
    // 如果和为奇数，比如 15 我们希望 奇数层和是7或者8 ，偶数层与奇数层的和相差就是1
    // 如果和为偶数，那么就是奇数层和偶数层的和相等
    // 现在解决怎么才能得到
    // 1到n比较特殊，所有的数字都会用上
    // k为奇数层或者偶数层节点数目
    public static int[] team(int n, int k) {
        int sum = n * (n + 1) / 2;
        int p1 = sum / 2;
        int p2 = (sum + 1) / 2;
        int[] ans = generate(p1, n, k);
        if (ans == null && ((sum & 1) == 1)) {
            ans = generate(p2, n, k);
        }
        return ans == null ? new int[]{-1} : ans;
    }

    // 一共有1到n这些数字
    // 需要选择k个，使得和为target
    public static int[] generate(int target, int n, int k) {
        // k个数字的最小和
        int minSumK = (k + 1) * k / 2;
        // 每个数字的提升幅度
        int range = n - k;
        if (target < minSumK || target > minSumK + k * range) {
            return null;
        }
        // 还需要多少
        int add = target - minSumK;
        int rightSize = add / range;
        int minIndex = (k - rightSize) + (add % range);
        int leftSize = k - rightSize - (add % range == 0 ? 0 : 1);
        int[] ans = new int[k];
        for (int i = 0; i < leftSize; i++) {
            ans[i] = i + 1;
        }
        if (add % range != 0) {
            ans[leftSize] = minIndex;
        }
        for (int i = k - 1, j = 0; j < rightSize; i--, j++) {
            ans[i] = n - j;
        }
        return ans;
    }
}
