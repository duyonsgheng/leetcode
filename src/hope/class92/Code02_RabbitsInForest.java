package hope.class92;

import java.util.Arrays;

/**
 * @author Mr.Du
 * @ClassName Code02_RabbitsInForest
 * @date 2024年09月10日 下午 03:35
 */
// 森林中的兔子
// 森林中有未知数量的兔子
// 你问兔子们一个问题: "还有多少只兔子与你颜色相同?"
// 你将答案收集到了一个数组answers中
// 你可能没有收集到所有兔子的回答，可能只是一部分兔子的回答
// 其中answers[i]是第i只兔子的答案
// 所有兔子都不会说错，返回森林中兔子的最少数量
// 测试链接 : https://leetcode.cn/problems/rabbits-in-forest/
public class Code02_RabbitsInForest {
    public static int numRabbits(int[] arr) {
        // 排序，按照词频来算
        Arrays.sort(arr);
        int n = arr.length;
        int ans = 0;
        for (int i = 0, j = 1, x; i < n; j++) {
            x = arr[i];
            while (j < n && arr[j] == x)
                j++;
            // 从 i到j-1就是一组，同一个答案
            ans += (j - i + x) / (x + 1) * (x + 1);
            i =j;
        }
        return ans;
    }
}
