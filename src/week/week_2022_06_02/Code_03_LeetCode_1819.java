package week.week_2022_06_02;

/**
 * @ClassName Code_03_LeetCode_1819
 * @Author Duys
 * @Description
 * @Date 2022/6/16 11:07
 **/
//1819. 序列中不同最大公约数的数目
// 给你一个由正整数组成的数组 nums 。
//数字序列的 最大公约数 定义为序列中所有整数的共有约数中的最大整数。
//例如，序列 [4,6,16] 的最大公约数是 2 。
//数组的一个 子序列 本质是一个序列，可以通过删除数组中的某些元素（或者不删除）得到。
//例如，[2,5,10] 是 [1,2,1,2,4,1,5,10] 的一个子序列。
//计算并返回 nums 的所有 非空 子序列中 不同 最大公约数的 数目 。
//链接：https://leetcode.cn/problems/number-of-different-subsequences-gcds
public class Code_03_LeetCode_1819 {

    // n/1 +n/2 +n/3 + n/4 +....+n/n-1 + n/n 收敛到 O(N*logN)
    public static int countDifferentSubsequenceGCDs(int[] nums) {
        int max = Integer.MIN_VALUE;
        for (int num : nums) {
            max = Math.max(max, num);
        }
        boolean[] set = new boolean[max + 1];
        for (int num : nums) {
            set[num] = true;
        }
        int ans = 0;
        for (int i = 1; i <= max; i++) {
            int cur = i;
            // 找到当前i的倍数，如果存在就从存在的位置开始尝试
            for (; cur <= max; cur += i) {
                if (set[cur]) {
                    break;
                }
            }
            // 每次步长增加
            for (int j = cur; j <= max; j += i) {
                if (!set[j]) {
                    continue;
                }
                cur = gcd(cur, j);
                if (cur == i) {
                    ans++;
                    break;
                }
            }
        }
        return ans;
    }

    public static int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }
}
