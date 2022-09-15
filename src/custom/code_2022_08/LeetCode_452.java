package custom.code_2022_08;

import java.util.Arrays;

/**
 * @ClassName LeetCode_452
 * @Author Duys
 * @Description
 * @Date 2022/8/12 13:14
 **/
// 452. 用最少数量的箭引爆气球
public class LeetCode_452 {

    public static int findMinArrowShots(int[][] points) {
        if (points == null || points[0] == null) {
            return 0;
        }
        int n = points.length;
        if (n == 1) {
            return 1;
        }
        // 结束位置排个序
        Arrays.sort(points, (a, b) -> a[1] - b[1]);
        int p = points[0][1];
        int ans = 1;
        for (int i = 1; i < n; i++) {
            // 就不需要使用剑了
            if (points[i][0] <= p && points[i][1] >= p) {
                continue;
            }
            p = points[i][1];
            ans++;
        }
        return ans;
    }

    public static void main(String[] args) {
        int[][] arr1 = {{10, 16}, {2, 8}, {1, 6}, {7, 12}};
        System.out.println(findMinArrowShots(arr1));
        int[][] arr2 = {{1, 2}, {3, 4}, {5, 6}, {7, 8}};
        System.out.println(findMinArrowShots(arr2));
        int[][] arr3 = {{1, 2}, {2, 3}, {3, 4}, {4, 5}};
        System.out.println(findMinArrowShots(arr3));
    }
}
