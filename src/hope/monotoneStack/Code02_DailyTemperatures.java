package hope.monotoneStack;

/**
 * @author Mr.Du
 * @ClassName Code02_DailyTemperatures
 * @date 2023年11月06日 10:04
 */
// 每日温度
// 给定一个整数数组 temperatures ，表示每天的温度，返回一个数组 answer
// 其中 answer[i] 是指对于第 i 天，下一个更高温度出现在几天后
// 如果气温在这之后都不会升高，请在该位置用 0 来代替。
// 测试链接 : https://leetcode.cn/problems/daily-temperatures/
public class Code02_DailyTemperatures {
    public static int MAXN = 100001;

    public static int[] stack = new int[MAXN];

    public static int r;

    public static int[] dailyTemperatures(int[] nums) {
        int n = nums.length;
        int[] ans = new int[n];
        r = 0;
        // [73,74,75,71,69,72,76,73]
        for (int i = 0, cur = 0; i < n; i++) {
            while (r > 0 && nums[stack[r - 1]] < nums[i]) {
                cur = stack[--r];
                // 几天后出现高温
                ans[cur] = i - cur;
            }
            stack[r++] = i;
        }
        return ans;
    }
}
