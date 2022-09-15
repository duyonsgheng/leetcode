package duys_code.day_42;

/**
 * @ClassName Code_04_296_LeetCode
 * @Author Duys
 * @Description
 * @Date 2022/1/4 13:01
 **/
public class Code_04_296_LeetCode {
    // 最佳的碰头地点

    // 所有的人在一个点碰头的最佳点
    // 最少的移动距离
    // 思路：
    // 比如：第一行一共10个人 第二行12个人  第n-1行 20个人，那么最佳的碰头一定不会是第一行，因为最后一行12个人 ，第一行10个人，12个人移动的距离肯定大于10个人的距离
    // 所以我们就先看看行，在看看列。最后行和列都确定了，那么点也就确定了
    public static int minTotalDistance(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        int[] rowOnes = new int[n];
        int[] colOnes = new int[m];
        // 统计每行每列的总共人数
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 1) {
                    rowOnes[i]++;
                    colOnes[j]++;
                }
            }
        }
        int total = 0;
        int i = 0;
        int j = n - 1;
        int iRest = 0;
        int jRest = 0;
        // 所有的人跨行的代价
        while (i < j) {
            if (rowOnes[i] + iRest < rowOnes[j] + jRest) {
                total += rowOnes[i] + iRest;
                iRest += rowOnes[i++];
            } else {
                total += rowOnes[j] + jRest;
                jRest += rowOnes[j--];
            }
        }

        // 所有人跨列的代价
        i = 0;
        j = m - 1;
        iRest = 0;
        jRest = 0;
        while (i < j) {
            if (colOnes[i] + iRest < colOnes[j] + jRest) {
                total += colOnes[i] + iRest;
                iRest += colOnes[i++];
            } else {
                total += colOnes[j] + jRest;
                jRest += colOnes[j++];
            }
        }
        return total;
    }
}
