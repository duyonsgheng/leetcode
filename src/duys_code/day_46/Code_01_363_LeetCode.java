package duys_code.day_46;

import java.util.TreeSet;

/**
 * @ClassName Code_01_363
 * @Author Duys
 * @Description 力扣：https://leetcode-cn.com/problems/max-sum-of-rectangle-no-larger-than-k/
 * @Date 2021/10/15 14:32
 **/
public class Code_01_363_LeetCode {
    /**
     * 题意：
     * 给一个二维矩阵，再给一个int k，求再矩阵中画矩形，所画的这个矩形里面所有数的和不超过K的最大数值是多少 sum<=K 返回sum
     */
    /**
     * 思路：
     * 我们想一个问题再一个数组中，我们怎么求 哪一个子数组的和是最接近k的，需要用到前缀和和有序表来做，
     * 例如 我们 已知 k= 20 整个数组长度20的和是100，0~10的前缀和是 5 那么11~20的前缀和就是15
     * 子数组问题通常是讨论以i位置结尾的所有子数组
     */
    public static int maxSumSubmatrix(int[][] matrix, int k) {
        if (matrix == null || matrix[0] == null) {
            return 0;
        }
        // 我们这个是O(N*M^2)的，所以 我们需要以列和行小的来做我们的平方项
        if (matrix.length > matrix[0].length) {
            matrix = rotate(matrix);
        }
        int N = matrix.length;
        int M = matrix[0].length;
        int ans = Integer.MIN_VALUE;
        TreeSet<Integer> set = new TreeSet<>();
        // 我们拿长的作为非平方项
        for (int row = 0; row < N; row++) {
            // 这里是数组压缩的东西就是当有多行的时候，每一列都加到当前来
            int[] colSum = new int[M];
            // 下面开始我们迭代所有的可能
            // 必须包含且只有0行;必须包含且只有0,1 行;必须包含且只有0,1,2行 .... 0...N-1
            // 必须包含且只有1行;必须包含且只有1,2 行;必须包含且只有1,2,3行..... 1...N-1
            for (int en = row; en < N; en++) {
                set.add(0);
                int sum = 0;
                // 这里我们就是按照每一行每一行的来处理，进行数组压缩，再二维数组中，上下相加，就是一个一个的矩形
                for (int col = 0; col < M; col++) {
                    // 数组压缩，把之前的对应行，列的数据加到当前处理数据的列上
                    colSum[col] += matrix[en][col];
                    sum += colSum[col];
                    Integer ceiling = set.ceiling(sum - k);
                    if (ceiling != null) {
                        ans = Math.max(ans, sum - ceiling);
                    }
                    set.add(sum);
                }
                set.clear();
            }

        }
        return ans;
    }

    public static int[][] rotate(int[][] matrix) {
        int N = matrix.length;
        int M = matrix[0].length;
        int[][] r = new int[M][N];
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                r[i][j] = matrix[j][i];
            }
        }
        return r;
    }

    // 这里我们写一个单独的数组时候怎么做
    public static int onlyOneArr(int[] arr, int k) {
        if (arr == null || arr.length < 1) {
            return Integer.MIN_VALUE;
        }
        TreeSet<Integer> set = new TreeSet<>();
        set.add(0);// 给一个0.因为当我们数组来到0位置的时候，这时候需要一个数来兜底
        int sum = 0;
        int ans = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
            // 再我们的有序表中去找已经加入的前缀和有没有满足 当前的大于 前缀和 - k 最接近的，如果存在，那么我们就找到了一个合规的
            // 比如[8,2,11,3,5] k = 20
            // i=0 那么最接近的是多少 就是 sum =8 , ceiling = 0 就是 ans = 8 ,set [0]
            // i=1 sum=10  ->  ceiling = 0 , ans  = 10 set [0,8]
            // i=2 sum = 21 -> ceiling = 10,set[0,8,10], ans =  13 了 -> set[0,8,10,21]
            Integer ceiling = set.ceiling(sum - k);
            if (ceiling != null) {
                int curSum = sum - ceiling;
                ans = Math.max(ans, curSum);
            }
            set.add(sum);
        }
        return ans;
    }
}
