package duys_code.day_17;

/**
 * @ClassName Code_02_FindKth
 * @Author Duys
 * @Description 力扣：https://leetcode-cn.com/problems/kth-smallest-element-in-a-sorted-matrix/
 * @Date 2021/10/28 15:29
 **/
public class Code_02_FindKth {
    /**
     * 给定一个每一行有序、每一列也有序，整体可能无序的二维数组
     * 在给定一个正数k，
     * 返回二维数组中，最小的第k个数
     */

    /**
     * 跟第一个题差不多，只是每走一步就要把走过比我小的，离我最近的都记录下来，并且统计比我小的有多少个
     */
    // 我们找到全局最小和全局最大的，在这之间来二分
    public int kthSmallest(int[][] matrix, int k) {
        int N = matrix.length;
        int M = matrix[0].length;
        int left = matrix[0][0];
        int right = matrix[N - 1][M - 1];
        int ans = 0;
        while (left <= right) {
            int mid = left + ((right - left) >> 1);
            // <=mid 有几个 <= mid 在矩阵中真实出现的数，谁最接近mid
            Info info = noMoreNum(matrix, mid);
            // 如果小于等于mid的个数都小于了k，说明我们的mid小了，需要把mid定大些，那我们的二分应该改
            if (info.num < k) {
                left = mid + 1;
            }
            //  // 如果小于等于mid的个数大于=了k，说明我们的mid可能大了，需要再划小一点
            else {
                right = mid - 1;
                ans = info.near;
            }
        }
        return ans;
    }

    public static class Info {
        public int near;
        public int num;

        public Info(int n1, int n2) {
            near = n1;
            num = n2;
        }
    }

    public static Info noMoreNum(int[][] matrix, int value) {
        int near = Integer.MIN_VALUE;
        int num = 0;
        int N = matrix.length;
        int M = matrix[0].length;
        int row = 0;
        int col = M - 1;
        while (row < N && col >= 0) {
            // 当前找到了了一个合适的。看看还有没有更合适的
            if (matrix[row][col] <= value) {
                // 比如3 行 4列 说明上面的和左边的都比我小
                near = Math.max(near, matrix[row][col]);
                num += col + 1;
                // 往下一行走
                row++;
            } else {
                col--;
            }
        }
        return new Info(near, num);
    }

}
