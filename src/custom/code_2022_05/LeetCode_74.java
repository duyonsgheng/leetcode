package custom.code_2022_05;

/**
 * @ClassName LeetCode_74
 * @Author Duys
 * @Description
 * @Date 2022/5/11 9:53
 **/
// 74. 搜索二维矩阵
// 编写一个高效的算法来判断m x n矩阵中，是否存在一个目标值。该矩阵具有如下特性：
//每行中的整数从左到右按升序排列。
//每行的第一个整数大于前一行的最后一个整数。
//链接：https://leetcode.cn/problems/search-a-2d-matrix
public class LeetCode_74 {

    public static boolean searchMatrix(int[][] matrix, int target) {

        int n = matrix.length;
        int m = matrix[0].length;
        int row = -1;
        // 我看每一行的以后一个值 先确定在哪一行
        for (int i = 0; i < n; i++) {
            int cur = matrix[i][m - 1];
            if (target <= cur) {
                row = i;
                break;
            }
        }
        if (row == -1) {
            return false;
        }
        // 二分这一列
        int[] rows = matrix[row];
        //
        int l = 0;
        int r = m - 1;
        while (l <= r) {
            int mid = (l + r) / 2;
            if (rows[mid] == target) {
                return true;
            } else if (rows[mid] > target) {
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        // [[1,3,5,7],[10,11,16,20],[23,30,34,60]]
        //3
        int[][] m = {{1}};
        int t = 1;
        System.out.println(searchMatrix(m, t));
    }
}
