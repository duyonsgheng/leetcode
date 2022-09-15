package week.week_2022_07_01;

/**
 * @ClassName Code_01_WindPrevent
 * @Author Duys
 * @Description
 * @Date 2022/7/7 17:14
 **/
// 来自真实笔试
// 给定一个二维数组matrix，数组中的每个元素代表一棵树的高度。
// 你可以选定连续的若干行组成防风带，防风带每一列的防风高度为这一列的最大值
// 防风带整体的防风高度为，所有列防风高度的最小值。
// 比如，假设选定如下三行
// 1 5 4
// 7 2 6
// 2 3 4
// 1、7、2的列，防风高度为7
// 5、2、3的列，防风高度为5
// 4、6、4的列，防风高度为6
// 防风带整体的防风高度为5，是7、5、6中的最小值
// 给定一个正数k，k <= matrix的行数，表示可以取连续的k行，这k行一起防风。
// 求防风带整体的防风高度最大值
public class Code_01_WindPrevent {

    // 窗口，每一列单独一个窗口，然后记录每个窗口里面的最大值
    public static int bestHeight(int[][] matrix, int k) {
        int n = matrix.length;
        int m = matrix[0].length;
        k = Math.min(k, n);
        // m个窗口
        int[][] windowMaxs = new int[m][n];
        int[][] windowLR = new int[m][2];
        for (int i = 0; i < k; i++) {
            addRow(matrix, m, i, windowMaxs, windowLR);
        }
        // 结算一下
        int ans = ans(matrix, m, windowMaxs, windowLR);
        for (int i = k; i < n; i++) {
            addRow(matrix, m, i, windowMaxs, windowLR);
            deleteRow(m, i - k, windowMaxs, windowLR);
            ans = Math.max(ans, ans(matrix, m, windowMaxs, windowLR));
        }
        return ans;
    }

    public static void addRow(int[][] arr, int m, int row, int[][] windowMaxs, int[][] windowLR) {
        for (int col = 0; col < m; col++) {
            while (windowLR[col][0] != windowLR[col][1] && arr[windowMaxs[col][windowLR[col][1] - 1]][col] <= arr[row][col]) {
                windowLR[col][1]--;
            }
            windowMaxs[col][windowLR[col][1]++] = row;
        }
    }

    public static void deleteRow(int m, int row, int[][] windowMaxs, int[][] windowLR) {
        for (int col = 0; col < m; col++) {
            if (windowMaxs[col][windowLR[col][0]] == row) {
                windowLR[col][0]++;
            }
        }
    }

    public static int ans(int[][] arr, int m, int[][] windowMaxs, int[][] windowLR) {
        int ans = Integer.MAX_VALUE;
        for (int col = 0; col < m; col++) {
            ans = Math.min(ans, arr[windowMaxs[col][windowLR[col][0]]][col]);
        }
        return ans;
    }
}
