package custom.code_2023_04;

/**
 * @ClassName LeetCode_1674
 * @Author Duys
 * @Description
 * @Date 2023/4/26 13:20
 **/
// 1674. 使数组互补的最少操作次数
public class LeetCode_1674 {
    public int minMoves(int[] nums, int limit) {
        int n = nums.length;
        int[] arr = new int[(limit << 1) + 2];
        for (int i = 0; i < (n >> 1); i++) {
            int n1 = nums[i], n2 = nums[n - i - 1];
            int min = Math.min(n1, n2);
            int max = Math.max(n1, n2);
            int sum = min + max;
            arr[1] += 2;
            arr[min + 1] -= 1;
            arr[max + limit + 1] += 1;
            arr[sum] -= 1;
            arr[sum + 1] += 1;
        }
        int ans = Integer.MAX_VALUE, sum = 0;
        for (int i = 1; i < arr.length - 1; i++) {
            sum += arr[i];
            ans = Math.min(ans, sum);
        }
        return ans;
    }
}
