package custom.code_2022_10;

import java.util.Arrays;

/**
 * @ClassName LeetCode_853
 * @Author Duys
 * @Description
 * @Date 2022/10/8 16:40
 **/
//车队 https://leetcode.cn/problems/car-fleet/
public class LeetCode_853 {
    public int carFleet(int target, int[] position, int[] speed) {
        int n = position.length;
        int[][] arr = new int[n][2];
        for (int i = 0; i < n; i++) {
            arr[i][0] = position[i];
            arr[i][1] = speed[i];
        }
        // 越靠近目标的先处理
        Arrays.sort(arr, (a, b) -> b[0] - a[0]);
        double pre = 0;
        int ans = 0;
        for (int i = 0; i < n; i++) {
            int[] cur = arr[i];
            double time = (double) (target - cur[0]) / cur[1];
            if (time > pre) { // 如果当前需要的时间比上一次要更多，追不上了
                ans++;
                pre = time;
            }
        }
        return ans;
    }


}
