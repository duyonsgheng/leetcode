package custom.code_2023_08;

import java.util.Arrays;
import java.util.TreeMap;

/**
 * @author Mr.Du
 * @ClassName LeetCode_2070
 * @date 2023年08月09日
 */
// 2070. 每一个查询的最大美丽值
// https://leetcode.cn/problems/most-beautiful-item-for-each-query/
public class LeetCode_2070 {
    public static int[] maximumBeauty(int[][] items, int[] queries) {
        Arrays.sort(items, (a, b) -> a[0] == b[0] ? a[1] - b[1] : a[0] - b[0]);
        // 每一种价格只保留最大的
        int n = queries.length;
        int[] ans = new int[n];
        // 把美丽值替换掉
        for (int i = 0, max = 0; i < items.length; i++) {
            if (items[i][1] <= max) {
                items[i][1] = max;
            } else {
                max = items[i][1];
            }
        }
        // 对每一个查询二分
        for (int i = 0; i < n; i++) {
            int l = 0, r = items.length - 1;
            while (l < r) {
                int m = l + (r - l) / 2 + 1;
                if (items[m][0] > queries[i]) {
                    r = m - 1;
                } else l = m;
            }
            ans[i] = items[l][0] <= queries[i] ? items[l][1] : 0;
        }
        return ans;
    }

    public static void main(String[] args) {
        // items = [[1,2],[3,2],[2,4],[5,6],[3,5]], queries = [1,2,3,4,5,6]
        int[] ints = maximumBeauty(new int[][]{{1, 2}, {3, 2}, {2, 4}, {5, 6}, {3, 5}}, new int[]{1, 2, 3, 4, 5, 6});
        System.out.println(ints);
    }
}
