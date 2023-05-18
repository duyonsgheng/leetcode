package custom.code_2023_05;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

/**
 * @ClassName LeetCode_1738
 * @Author Duys
 * @Description
 * @Date 2023/5/16 15:07
 **/
// 1738. 找出第 K 大的异或坐标值
public class LeetCode_1738 {
    // 5 2
    // 1 6
    public static int kthLargestValue(int[][] matrix, int k) {
        int m = matrix.length, n = matrix[0].length;
        int[][] pre = new int[m + 1][n + 1];
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        for (int i = 1; i <= m; ++i) {
            for (int j = 1; j <= n; ++j) {
                pre[i][j] = pre[i - 1][j] ^ pre[i][j - 1] ^ pre[i - 1][j - 1] ^ matrix[i - 1][j - 1];
                if (queue.size() < k) {
                    queue.add(pre[i][j]);
                } else {
                    if (pre[i][j] > queue.peek()) {
                        queue.poll();
                        queue.add(pre[i][j]);
                    }
                }
            }
        }
        return queue.peek();
    }
    // 0 0 0
    // 0 0 0
    // 101
    // 111
    // 010
    // 0 0  0

    public static void main(String[] args) {
        int[][] arr = new int[][]{{5, 2}, {1, 6}};
        // 5 7
        // 1 7
        System.out.println(kthLargestValue(arr, 1));
        System.out.println(kthLargestValue(arr, 2));
        System.out.println(kthLargestValue(arr, 3));
    }
}
