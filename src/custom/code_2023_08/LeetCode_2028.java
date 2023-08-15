package custom.code_2023_08;

/**
 * @author Mr.Du
 * @ClassName LeetCode_2028
 * @date 2023年08月01日
 */
// 2028. 找出缺失的观测数据
// https://leetcode.cn/problems/find-missing-observations/
public class LeetCode_2028 {
    // rolls = [3,2,4,3], mean = 4, n = 2
    public static int[] missingRolls(int[] rolls, int mean, int n) {
        int m = rolls.length;
        int all = (n + m) * mean;
        for (int i : rolls) {
            all -= i;
        }
        if (all > 6 * n || all < n) {
            return new int[0];
        }
        // rolls = [1,5,6], mean = 3, n = 4
        int p = all / n, q = all % n;
        int[] ans = new int[n];
        for (int i = 0; i < n; i++) {
            ans[i] = p + (i < q ? 1 : 0);
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] ints1 = missingRolls(new int[]{6, 3, 4, 3, 5, 3}, 1, 6);
        System.out.println();
    }
}
