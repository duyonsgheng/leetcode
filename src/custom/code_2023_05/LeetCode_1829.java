package custom.code_2023_05;

/**
 * @ClassName LeetCode_1829
 * @Author Duys
 * @Description
 * @Date 2023/5/25 11:16
 **/
// 1829. 每个查询的最大异或值
public class LeetCode_1829 {
    public int[] getMaximumXor(int[] nums, int maximumBit) {
        int n = nums.length;
        int mask = (1 << maximumBit) - 1;
        int[] ans = new int[n];
        int cur = 0;
        int index = n;
        while (index != 0) {
            cur ^= nums[n - index];
            ans[--index] = mask - cur & mask;
        }
        return ans;
    }
}
