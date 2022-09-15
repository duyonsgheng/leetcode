package duys.class_07_27;

/**
 * @ClassName SplitMumber
 * @Author Duys
 * @Description 正数拆分问题
 * @Date 2021/7/28 16:29
 **/
public class SplitMember {
    /**
     * 给一个正数M 要求把M分解，但是分解过程中，子项之间关系是，后面项不能小于前边项的，升序行为。。。
     * 比如 3 ： 1+1+1，1+2 ，3 三种，不能出现2+1这种方式
     * 比如 4 ： 1+1+1+1 ，1+3，2+2 1+1+2  4 总共5种
     */
    public static int splitMember(int n) {
        if (n < 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        // 意思，就是6 已经拆了1 还剩下5待拆
        return split1(1, n);
    }

    // 上一个拆出来的数pre，还剩下rest需要去拆解
    public static int split1(int pre, int rest) {
        // 比如 5 已经拆到了3 剩下2 不能拆了，不然违反原则了
        if (rest == 0) {
            return 1;
        }
        if (pre > rest) {
            return 0;
        }
        if (pre == rest) {
            return 1;
        }
        int ans = 0;
        for (int i = pre; i <= rest; i++) {
            ans += split1(i, rest - i);
        }
        return ans;
    }

    public static int dp1(int n) {
        if (n < 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        // pre 是 1-n之间的
        // rest 是0-n之间的
        int[][] dp = new int[n + 1][n + 1];
        // 根据暴力递归的方法改写
        for (int pre = 1; pre <= n; pre++) {
            dp[pre][0] = 1; // 第0列的值全部是1
            // 对角线位置
            dp[pre][pre] = 1;
        }
        // pre - 行 ,从下往上填
        for (int pre = n - 1; pre >= 1; pre--) {
            // 列  // pre > rest的全部是0，数据初始化就是0，不用管
            for (int rest = pre + 1; rest <= n; rest++) {
                int ans = 0;
                // 存在枚举行为，需要找严格位置依赖
                for (int i = pre; i <= rest; i++) {
                    ans += dp[i][rest - i];
                }
                dp[pre][rest] = ans;
            }
        }
        return dp[1][n];
    }

    /**
     * 例如 n = 6
     * -- 0  1  2  3  4  5  6  rest
     * 0  -  -  -  -  -  -  -
     * 1  1  1           x
     * 2  1    1         a
     * 3  1        1
     * 4  1           1
     * 5  1             1
     * 6  1                1
     * pre
     */
    /**
     * x位置是 [1,5] 依赖[1,4][2,3][3,2][4,1][5,0]
     * 因为我们是从下往上填的所以观察a位置
     * a位置是 [2,5] 依赖[2,3][3,2][4,1][5,0]
     * 得出x位置的值等于[2,3]位置+[2,5]位置就好了
     */

    public static int dp2(int n) {
        if (n < 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        // pre 是 1-n之间的
        // rest 是0-n之间的
        int[][] dp = new int[n + 1][n + 1];
        // 根据暴力递归的方法改写
        for (int pre = 1; pre <= n; pre++) {
            dp[pre][0] = 1; // 第0列的值全部是1
            // 对角线位置
            dp[pre][pre] = 1;
        }
        // pre - 行 ,从下往上填
        for (int pre = n - 1; pre >= 1; pre--) {
            // 列  // pre > rest的全部是0，数据初始化就是0，不用管
            for (int rest = pre + 1; rest <= n; rest++) {
                dp[pre][rest] = dp[pre + 1][rest] + dp[pre][rest - pre];
            }
        }
        return dp[1][n];
    }

    public static void main(String[] args) {
        System.out.println(splitMember(14));
        System.out.println(dp1(14));
        System.out.println(dp2(14));

    }


}
