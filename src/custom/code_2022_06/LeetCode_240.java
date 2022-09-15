package custom.code_2022_06;

/**
 * @ClassName LeetCode_240
 * @Author Duys
 * @Description
 * @Date 2022/6/10 16:41
 **/
// 240. 搜索二维矩阵 II
// 每行的元素从左到右升序排列。
// 每列的元素从上到下升序排列
// https://leetcode.cn/problems/search-a-2d-matrix-ii/
public class LeetCode_240 {

    // 使用z查找
    public static boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix[0] == null) {
            return false;
        }
        int n = matrix.length;
        int m = matrix[0].length;
        int x = 0;
        int y = m - 1;
        while (x < n && y >= 0) {
            if (matrix[x][y] == target) {
                return true;
            }
            if (matrix[x][y] > target) {
                y--;
            } else {
                x++;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int[][] m = {{1, 1}};
        System.out.println(searchMatrix(m, 2));
    }

}
