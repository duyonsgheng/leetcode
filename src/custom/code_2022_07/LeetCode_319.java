package custom.code_2022_07;

import java.util.Arrays;

/**
 * @ClassName LeetCode_319
 * @Author Duys
 * @Description
 * @Date 2022/7/19 14:50
 **/
// 319. 灯泡开关
// 初始时有n 个灯泡处于关闭状态。第一轮，你将会打开所有灯泡。接下来的第二轮，你将会每两个灯泡关闭第二个。
//第三轮，你每三个灯泡就切换第三个灯泡的开关（即，打开变关闭，关闭变打开）。第 i 轮，你每 i 个灯泡就切换第 i 个灯泡的开关。直到第 n 轮，你只需要切换最后一个灯泡的开关。
//找出并返回 n轮后有多少个亮着的灯泡。
//链接：https://leetcode.cn/problems/bulb-switcher
public class LeetCode_319 {

    public static int bulbSwitch(int n) {
        // dp 往下推
        if (n <= 1) {
            return n;
        }
        int[] dp = new int[n];
        // 第一轮
        Arrays.fill(dp, 1);
        for (int i = 2; i <= n; i++) {
            int index = i - 1;
            while (index < n) {
                dp[index] ^= 1;
                index = index + i;
            }
        }
        int ans = 0;
        for (int i = 0; i < n; i++) {
            if (dp[i] == 1) {
                ans++;
            }
        }
        return ans;
    }

    public static int bulbSwitch1(int n) {
        return (int) Math.sqrt(n);
    }

    public static void main(String[] args) {
        for (int i = 5; i < 10000; i += 2) {
            System.out.println(bulbSwitch1(i));
            System.out.println(bulbSwitch(i));
            System.out.println();
        }
    }
}
