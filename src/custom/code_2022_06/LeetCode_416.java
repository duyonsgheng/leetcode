package custom.code_2022_06;

/**
 * @ClassName LeetCode_416
 * @Author Duys
 * @Description
 * @Date 2022/6/13 14:55
 **/
//416. 分割等和子集
// 给你一个 只包含正整数 的 非空 数组 nums 。请你判断是否可以将这个数组分割成两个子集，使得两个子集的元素和相等。
public class LeetCode_416 {

    public static boolean canPartition(int[] nums) {
        if (nums == null || nums.length < 1) {
            return false;
        }
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        if ((sum & 1) != 0) {
            return false;
        }
        return process(nums, 0, sum >> 1);
    }

    public static boolean process(int[] arr, int index, int rest) {
        if (rest == 0) {
            return true;
        }
        if (arr.length == index) {
            return false;
        }
        boolean p1 = process(arr, index + 1, rest);
        boolean p2 = false;
        if (rest >= arr[index]) {
            p2 = process(arr, index + 1, rest - arr[index]);
        }
        return p1 | p2;
    }

    public static boolean canPartition1(int[] nums) {
        if (nums == null || nums.length < 1) {
            return false;
        }
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        if ((sum & 1) != 0) {
            return false;
        }
        int n = nums.length;
        int m = sum >> 1;
        boolean[][] dp = new boolean[n + 1][m + 1];
        dp[n][0] = true;
        for (int i = n - 1; i >= 0; i--) {
            for (int j = 0; j <= m; j++) {
                dp[i][j] = dp[i + 1][j];
                if (nums[i] <= j) {
                    dp[i][j] |= dp[i + 1][j - nums[i]];
                }
            }
        }
        return dp[0][m];
    }

    public static void main(String[] args) {
        int[] arr = {100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 99, 97};

        //System.out.println(canPartition(arr));
        System.out.println(canPartition1(arr));
    }
}
