package duys_code.day_09;


/**
 * @ClassName Code_03_LIS
 * @Author Duys
 * @Description 力扣：https://leetcode-cn.com/problems/longest-increasing-subsequence/
 * @Date 2021/10/13 20:26
 **/
public class Code_03_LIS {
    /**
     * 子序列，可以不连续，从左往右选择
     */
    public static int lengthOfLIS(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        if (nums.length == 1) {
            return 1;
        }
        int n = nums.length;
        // ends 数据解释
        // 比如 数组 3 2 1 3 4 5 2
        // 来到3的时候 ends数组为空。把ends[0] = 3. dp[0] = 1
        // 来到2的时候，发现ends数组最小是3，当前是2，不能把递增序列扩长，但是当前是小于之前的，可以替换掉3，所以ends[0] = 2
        // 来到1的时候，发现ends数组有效区最小是2，不能扩展有效区，所以ends[0] =1
        // 来到3的时候，有效区最小是1.，当前是3 比1大，有效区扩展 ends[1] =3，当前的长度可以增加1
        // 来到4的时候，有效区最后一个是3，当前是4，当前的长度增长1，有效区扩展
        // 依次推下去
        // 最后一个2的时候，在有效区二分查找，刚刚大于2的。然后收集有效区内最左边到刚刚大于2的位置，有几个数，当前的长度就是几，并且需要把比自己小的位置改掉为2
        int[] ends = new int[n];
        ends[0] = nums[0];
        int right = 0;
        int l = 0;
        int r = 0;
        int m = 0;
        int max = 1;
        // 4, 10, 4, 3, 8, 9
        // 1
        for (int i = 1; i < n; i++) {
            l = 0;
            r = right;
            while (l <= r) {
                m = (l + r) >> 1;
                if (nums[i] > ends[m]) {
                    l = m + 1;
                } else {
                    r = m - 1;
                }
            }
            System.out.println("i -> " + i + "right -> " + right + " l -> " + l);
            right = Math.max(right, l);
            ends[l] = nums[i];
            max = Math.max(max, l + 1);
        }
        return max;
    }

    public static int lengthOfLIS2(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        if (nums.length == 1) {
            return 1;
        }
        int N = nums.length;
        // dp[i] - 含义：以i位置结尾的所有子序列，最长递增子序列长度
        int[] dp = new int[N];
        for (int i = 0; i < N; i++) {
            dp[i] = 1;
        }
        if (nums[1] > nums[0]) {
            dp[1] = 2;
        }
        // 4,5,1,2,3 - i = 4 找比我第二小的
        for (int i = 2; i < N; i++) {
            //从dp中找出比我小的，也就是整个中第二大的
            // 去我的左边找第二小的
            int index = i - 1;
            int cha = 0;
            while (index >= 0) {
                cha = nums[i] - nums[index];
                if (cha > 0) {
                    dp[i] = Math.max(dp[index] + 1, dp[i]);
                }
                index--;
            }
        }
        int ans = 1;
        for (int i : dp) {
            ans = Math.max(ans, i);
        }
        return ans;
    }


    public static void main(String[] args) {
        int[] nums = {4, 10, 4, 3, 8, 9};
        System.out.println(lengthOfLIS(nums));

    }


}
