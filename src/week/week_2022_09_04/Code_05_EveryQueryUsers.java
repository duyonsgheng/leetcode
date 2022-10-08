package week.week_2022_09_04;

/**
 * @ClassName Code_05_EveryQueryUsers
 * @Author Duys
 * @Description
 * @Date 2022/9/29 14:07
 **/
// 来自字节
// 给定正数N，表示用户数量，用户编号从0~N-1
// 给定正数M，表示实验数量，实验编号从0~M-1
// 给定长度为N的二维数组A，
// A[i] = { a, b, c }表示，用户i报名参加了a号、b号、c号实验
// 给定正数Q，表示查询的条数
// 给定长度为Q的二维数组B，
// B[i] = { e, f }表示，第i条查询想知道e号、f号实验，一共有多少人(去重统计)
// 返回每一条查询的结果数组
// 数据描述 :
// 1 <= N <= 10^5
// 1 <= M <= 10^2
// 1 <= Q <= 10^4
// 所有查询所列出的所有实验编号数量(也就是二维数组B，行*列的规模) <= 10^5
public class Code_05_EveryQueryUsers {

    // 利用位图来实现
    // 我们需要查询任务对应报名的人，去重
    public static int[] record(int n, int m, int q, int[][] A, int[][] B) {
        // n 一共多少人
        // 任何一个实现，所有人都参加，用多少整数来表示
        int parts = (n + 31) / 32; // 向上取整
        // dp表示不同的人，参加人的表示形式，每一个整数可以表示32个人
        int[][] bitMap = new int[m][parts];
        for (int i = 0; i < n; i++) {
            // 当前用户，参加了哪些实验
            for (int j : A[i]) {
                // 对应的实验，哪些人参加了，全部记录进去
                bitMap[j][i / 32] |= 1 << (i % 32);
            }
        }
        int[] ans = new int[q];
        for (int i = 0; i < q; i++) {
            // i号查询，要查询哪些实验的
            int all = 0;
            // 总共多少组用户
            for (int j = 0; j < parts; j++) {
                int status = 0;
                // 当前查询，查询了哪些实验，然后统计本次查询的人数
                for (int k : B[i]) {
                    status |= bitMap[k][j];
                }
                // 累到当前查询的答案上去
                all += countOnes(status);
            }
            ans[i] = all;
        }
        return ans;
    }

    public static int countOnes(int n) {
        n = (n & 0x55555555) + ((n >>> 1) & 0x55555555);
        n = (n & 0x33333333) + ((n >>> 2) & 0x33333333);
        n = (n & 0x0f0f0f0f) + ((n >>> 4) & 0x0f0f0f0f);
        n = (n & 0x00ff00ff) + ((n >>> 8) & 0x00ff00ff);
        n = (n & 0x0000ffff) + ((n >>> 16) & 0x0000ffff);
        return n;
    }
}
