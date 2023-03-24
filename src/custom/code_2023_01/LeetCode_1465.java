package custom.code_2023_01;

import java.util.Arrays;

/**
 * @ClassName LeetCode_1465
 * @Author Duys
 * @Description
 * @Date 2023/1/31 10:44
 **/
public class LeetCode_1465 {
    // 找出跨度最大的区域
    int mod = 1_000_000_007;

    public int maxArea(int h, int w, int[] horizontalCuts, int[] verticalCuts) {
        long hMax = 0, vMax = 0;
        Arrays.sort(horizontalCuts);
        Arrays.sort(verticalCuts);
        // 横切跨度最大的
        for (int i = 1; i < horizontalCuts.length; i++) {
            hMax = Math.max(hMax, horizontalCuts[i] - horizontalCuts[i - 1]);
        }
        // 竖切跨度最大的
        for (int i = 1; i < verticalCuts.length; i++) {
            vMax = Math.max(vMax, verticalCuts[i] - verticalCuts[i - 1]);
        }
        // 边界
        hMax = Math.max(hMax, horizontalCuts[0]);
        hMax = Math.max(hMax, h - horizontalCuts[horizontalCuts.length - 1]);

        vMax = Math.max(vMax, verticalCuts[0]);
        vMax = Math.max(vMax, w - verticalCuts[verticalCuts.length - 1]);
        return (int) ((hMax * vMax) % mod);
    }
}
