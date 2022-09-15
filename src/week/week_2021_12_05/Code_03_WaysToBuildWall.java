package week.week_2021_12_05;

/**
 * @ClassName Code_03_WaysToBuildWall
 * @Author Duys
 * @Description
 * @Date 2022/4/13 13:44
 **/

// 来自hulu
// 你只有1*1、1*2、1*3、1*4，四种规格的砖块
// 你想铺满n行m列的区域，规则如下：
// 1）不管那种规格的砖，都只能横着摆
// 比如1*3这种规格的砖，3长度是水平方向，1长度是竖直方向
// 2）会有很多方法铺满整个区域，整块区域哪怕有一点点不一样，就算不同的方法
// 3）区域内部(不算区域整体的4条边界)，不能有任何砖块的边界线，是从上一直贯穿到下的直线
// 返回符合三条规则下，铺满n行m列的区域，有多少种不同的摆放方法
public class Code_03_WaysToBuildWall {

    public static long[] r = {0, 1, 2, 4, 8};

    public static long ways(int n, int m) {
        if (n <= 1 || m <= 1) {
            return 1;
        }
        // 只有1行的情况下，有i列，总共多少中摆法
        long[] len = new long[m + 1];
        for (int i = 1; i <= Math.min(4, m); i++) {
            len[i] = r[i];
        }
        for (int i = 5; i <= m; i++) {
            len[i] = len[i - 1] + len[i - 2] + len[i - 3] + len[i - 4];
        }

        // 一共有n行，i列的情况下，怎么摆，所有的摆法
        long[] any = new long[m + 1];
        for (int i = 1; i <= m; i++) {
            any[i] = power(len[i], n);
        }
        // solid[i] = 一共有n行的情况下，列的长度为i的时候有几种合法的摆法
        long[] solid = new long[m + 1];
        solid[1] = 1;
        for (int i = 2; i <= m; i++) {
            long invalid = 0;
            // N * i
            // 1) （N * 1 合法） * （N * (i-1) 总共）
            // 2) （N * 2 合法） * （N * (i-2) 总共）
            // 3) （N * 3 合法） * （N * (i-3) 总共）
            //
            // j) （N * j 合法） * （N * (i-j) 总共）
            for (int j = 1; j < i; j++) {
                invalid += solid[j] * any[i - j];
            }
            // 所有的减去不合法的，就是合法的了
            solid[i] = any[i] - invalid;
        }
        return solid[m];
    }

    // 算 base的power次方
    public static long power(long base, int power) {
        long ans = 1;
        while (power != 0) {
            if ((power & 1) != 0) {
                ans *= base;
            }
            base *= base;
            power >>= 1;
        }
        return ans;
    }
}
