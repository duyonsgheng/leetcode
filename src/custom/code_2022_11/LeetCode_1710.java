package custom.code_2022_11;

import java.util.Arrays;

/**
 * @ClassName LeetCode_1710
 * @Author Duys
 * @Description
 * @Date 2022/11/15 9:43
 **/
// 1710. 卡车上的最大单元数
public class LeetCode_1710 {
    public int maximumUnits(int[][] boxTypes, int truckSize) {
        // 把单元数量大的排在前面
        Arrays.sort(boxTypes, (a, b) -> b[1] - a[1]);
        int ans = 0;
        for (int[] cur : boxTypes) {
            int cur1 = cur[0];
            int cur2 = cur[1];
            if (cur1 < truckSize) {
                ans += cur1 * cur2;
                truckSize -= cur1;
            } else {
                // 剩下几个，全部装这个 单元大的
                ans += truckSize * cur2;
                break;
            }
        }
        return ans;
    }
}
