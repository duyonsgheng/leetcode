package custom.code_2022_08;

/**
 * @ClassName LeetCode_453
 * @Author Duys
 * @Description
 * @Date 2022/8/12 13:45
 **/
// 453. 最小操作次数使数组元素相等
// 给你一个长度为 n 的整数数组，每次操作将会使 n - 1 个元素增加 1 。返回让数组所有元素相等的最小操作次数。
public class LeetCode_453 {
    // 思考一个问题：从最小值开始，每次一遍，最多变到 min+n
    public int minMoves(int[] nums) {
        int min = Integer.MAX_VALUE;
        for (int i : nums) {
            min = Math.min(i, min);
        }
        int ans = 0;
        for (int i : nums) {
            ans += i - min;
        }
        return ans;
    }
}
