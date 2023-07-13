package custom.code_2023_06;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

/**
 * @author Mr.Du
 * @ClassName LeetCode_2679
 * @date 2023年07月04日
 */
// 2679. 矩阵中的和
public class LeetCode_2679 {
    public static int matrixSum(int[][] nums) {
        int n = nums.length;
        int m = nums[0].length;
        List<PriorityQueue<Integer>> list = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            list.add(new PriorityQueue<>((a, b) -> b - a));
        }
        // 每一行排序
        for (int i = 0; i < n; i++) {
            //int[] cur = nums[i];
            Arrays.sort(nums[i]);
            //nums[i] = cur;
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // 每一列
                list.get(i).add(nums[j][i]);
            }
        }
        int ans = 0;
        for (int i = 0; i < m; i++) {
            ans += list.get(i).peek();
        }
        return ans;
    }

    public static void main(String[] args) {
        // [[7,2,1],[6,4,2],[6,5,3],[3,2,1]]
        System.out.println(matrixSum(new int[][]{{7, 2, 1}, {6, 4, 2}, {6, 5, 3}, {3, 2, 1}}));
    }
}
