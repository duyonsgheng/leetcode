package duys.class_09_08;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName TSP
 * @Author Duys
 * @Description
 * @Date 2021/9/8 13:38
 **/
public class TSP {
    /**
     * 题意：
     * TSP问题 有N个城市，任何两个城市之间的都有距离，任何一座城市到自己的距离都为0。所有点到点的距 离都存在一个N*N的二维数组matrix里，
     * 也就是整张图由邻接矩阵表示。现要求一旅行商从k城市 出发必须经过每一个城市且只在一个城市逗留一次，最后回到出发的k城，返回总距离最短的路的 距离。参数给定一个matrix，给定k。
     */
    // 这个matrix是一个以对角线对称的N *N 的矩阵

    // 1.选择 0 这个城市为全局初始点，也就是全局归宿点，
    public static int func1(int[][] matrix) {
        List<Integer> set = new ArrayList<>();
        for (int i = 0; i < matrix.length; i++) {
            set.add(1);
        }
        // 0 这个城市为全局初始点，也就是全局归宿点
        return process1(matrix, set, 0);
    }

    // set是还存在城市，start是子过程的出发点
    public static int process1(int[][] matrix, List<Integer> set, int start) {
        // 统计还剩下的城市
        int cityNum = 0;
        for (int i = 0; i < set.size(); i++) {
            if (set.get(i) != null) {
                cityNum++;
            }
        }
        // 如果只剩下一座城市了，直接返回回全局归宿点的距离,base case
        if (cityNum == 1) {
            return matrix[start][0];
        }
        // 当前过程的开始位置是satrt，那么已经来到了start，所以start就应该去掉了，然后选择其他的子过程
        set.set(start, null);
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < set.size(); i++) {
            if (set.get(i) != null) {
                // 后续的距离：从当前位置到 i的距离，然后 后续从i出发的距离
                int cur = matrix[start][i] + process1(matrix, set, i);
                min = Math.min(min, cur);
            }
        }
        // 还原现场
        set.set(start, 1);
        return min;
    }


    // 使用状态压缩，之前使用的List来表示哪些城市已经去过了，哪些城市还没去过，这里使用一个int的二进制位信息来表示
    // 比如 7 座城市，都还没去过 7 -> 1 1 1 1 1 1 1
    public static int func2(int[][] matrix) {
        int N = matrix.length;
        int allCity = 1 << N - 1;
        return process2(matrix, allCity, 0);
    }

    public static int process2(int[][] matrix, int cityStatus, int start) {

        // 如果只有一座城市了，直接返回当前start到全局归宿点0号城市的距离
        // cityStatus & (~cityStatus + 1) 提取cityStatus最右侧的1
        if (cityStatus == (cityStatus & (~cityStatus + 1))) {
            return matrix[start][0];
        }
        // 开始枚举
        // 1.先把当前来到的城市start位置上的1给变成0
        // 将1右移到start位置上，然后取反，和原的status相与，去掉了start位置上的1
        cityStatus &= ~(1 << start);
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < matrix.length; i++) {
            if ((cityStatus & (1 << i)) != 0) { // 这个位置还有
                // 后续的距离：从当前位置到 i的距离，然后 后续从i出发的距离
                int cur = matrix[start][i] + process2(matrix, cityStatus, i);
                min = Math.min(min, cur);
            }
        }
        // 现场还原
        cityStatus |= (1 << start);
        return min;
    }

    // 状态压缩+dp 记忆化搜索，把可变参数变成线性的
    public static int func3(int[][] matrix) {
        int N = matrix.length;
        int allCity = 1 << N - 1;
        int[][] dp = new int[1 << N][N];
        for (int i = 0; i < (1 << N); i++) {
            for (int j = 0; j < N; j++) {
                dp[i][j] = -1;
            }
        }
        return process3(matrix, allCity, 0, dp);
    }

    public static int process3(int[][] matrix, int status, int start, int[][] dp) {
        if (dp[status][start] != -1) {
            return dp[status][start];
        }
        // 只剩下一座城市了，提取最右侧的1
        if (status == (status & (~status + 1))) {
            // 我们选择的是0为全局归宿点
            dp[status][start] = matrix[start][0];
        } else {
            // 先把status的start改为0
            status &= ~(1 << start);
            int min = Integer.MAX_VALUE;
            for (int i = 0; i < matrix.length; i++) {
                if ((status & (1 << i)) != 0) { // 这个位置还有
                    // 后续的距离：从当前位置到 i的距离，然后 后续从i出发的距离
                    int cur = matrix[start][i] + process3(matrix, status, i, dp);
                    min = Math.min(min, cur);
                }
            }
            // 现场还原
            status |= (1 << start);
            dp[status][start] = min;
        }
        return dp[status][start];
    }

    // 分析dp的位置依赖
    public static int func4(int[][] matrix) {
        return 0;
    }

}
