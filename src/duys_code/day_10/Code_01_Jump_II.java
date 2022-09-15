package duys_code.day_10;

/**
 * @ClassName Code_01_Jupm_II
 * @Author Duys
 * @Description https://leetcode-cn.com/problems/jump-game-ii/
 * @Date 2021/10/14 13:31
 **/
public class Code_01_Jump_II {
    /**
     * 青蛙跳跃的另种
     */
    // 2,1,1,3,5,3,2,6,3,2,1,4
    public static int jump(int[] nums) {
        if (nums == null || nums.length < 1) {
            return 0;
        }
        int step = 0;
        int cur = 0; // step 步 最多能去哪里
        int next = 0;// 如果step能多走一步，能去到的位置
        for (int i = 0; i < nums.length; i++) {
            // 当前来到0位置
            if (cur < i) {
                step++;
                cur = next;
            }
            // next表示我如果再跳一步，能去的最大位置
            next = Math.max(next, i + nums[i]);
        }
        return step;
    }

    public static int jump2(int[] nums) {
        if (nums == null || nums.length < 1) {
            return 0;
        }
        int step = 0;
        int n = nums.length - 1;
        int curMax = 0; // 当前来到的位置最远能去哪里
        int end = 0; // 上一步跳的最远的距离
        for (int i = 0; i < n; i++) {
            curMax = Math.max(curMax, i + nums[i]); // 我来到的位置最远能去到哪个位置
            if (curMax >= n) { // 只需要跳一步就结束
                return step + 1;
            }
            // 我上一步就能来到，我要重新跳了，否则，还在上一步最远的距离范围内
            if (end == i) {
                // 跳了一步
                step++;
                // 更新
                end = curMax;
            }
        }
        return step;
    }
}
