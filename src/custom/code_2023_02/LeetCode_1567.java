package custom.code_2023_02;

/**
 * @ClassName LeetCode_1567
 * @Author Duys
 * @Description
 * @Date 2023/2/17 10:12
 **/
// 1567. 乘积为正数的最长子数组长度
public class LeetCode_1567 {
    // [-1,-2,-3,0,1]
    // 窗口
    public static int getMaxLen(int[] nums) {
        int ans = 0;
        int l = 0;
        boolean flag = true;
        for (int r = 0; r < nums.length; r++) {
            if (nums[r] == 0) { // 看看之前的窗口啥样子
                if (!flag) { // 如果当前乘积是负数
                    // 窗口缩进，找到一个负数就停止，当前整个窗口乘积是负数，除以一个负数，就是正数了
                    while (l < r && nums[l++] >= 0) ;
                }
                // 当前r为0，那么就是r-1-l+1
                ans = Math.max(ans, r - l);
                l = r + 1;
                flag = true; // 下一次从新开始了
            } else {
                if (nums[r] < 0) {
                    flag = !flag;
                }
                if (flag) {
                    ans = Math.max(ans, r - l + 1);
                }
            }
        }
        if (!flag) {
            while (l < nums.length && nums[l++] >= 0) ;
        }
        return Math.max(ans, nums.length - l);
    }

    public static int getMaxLen1(int[] nums) {
        int len = nums.length;
        int positive = nums[0] > 0 ? 1 : 0; // 正数
        int negative = nums[0] < 0 ? 1 : 0; // 负数
        int ans = positive;
        for (int i = 1; i < len; i++) {
            if (nums[i] > 0) {
                positive++;
                negative = negative > 0 ? negative + 1 : 0;
            } else if (nums[i] < 0) {
                // 当前数小于0，乘积的话，就是之前的负数+1，就是正数了，所以正负互换
                int np = negative > 0 ? negative + 1 : 0;
                int nn = positive + 1;
                positive = np;
                negative = nn;
            } else {
                positive = 0;
                negative = 0;
            }
            ans = Math.max(ans, positive);
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(getMaxLen(new int[]{1, -2, -3, 4}));
        System.out.println(getMaxLen(new int[]{0, 1, -2, -3, -4}));
        System.out.println(getMaxLen(new int[]{-1, -2, -3, 0, 1}));
        // -1 2
    }
}
