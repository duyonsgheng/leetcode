package duys_code.day_50;

/**
 * @ClassName Code_01_568_LeetCode
 * @Author Duys
 * @Description 力扣：https://leetcode-cn.com/problems/maximum-vacation-days/
 * @Date 2021/10/29 11:19
 **/
public class Code_01_568_LeetCode {
    /**
     * 两个二维数组
     * flights[i][j] 表示 i到j号成如果=0 表示 航空状态是没有航班的 = 1表示有航班
     * days[i][j] 表示 在第j个星期，在第i号城市休假天数
     */

    // dp[i][j]的含义是第i星期必须在第j号城市，那么i-1星期在能到j号城市去的哪些城市中求一个最大的然后+上j号城市的休假天数
    public static int maxVacationDays(int[][] fly, int[][] day) {
        // 城市数量
        int n = fly.length;
        // 多少周
        int k = day[0].length;
        // 计算一下，能到当前城市 有哪些城市
        int[][] pass = new int[n][];
        for (int i = 0; i < n; i++) {
            int s = 0;
            for (int j = 0; j < n; j++) {
                // j能不能到i号城市
                if (fly[j][i] != 0) {
                    s++;
                }
            }
            pass[i] = new int[s];
            for (int j = n - 1; j >= 0; j--) {
                if (fly[j][i] != 0) {
                    pass[i][--s] = j;
                }
            }
        }
        // dp[i][j] --> 第i周必须在第j号城市的最大休假天数
        int[][] dp = new int[k][n];
        // 第0周必须要在第0号城市的天数
        dp[0][0] = day[0][0];
        // 第一个星期 : 星期一从城市0飞到城市1，玩6天，工作1天。
        //（虽然你是从城市0开始，但因为是星期一，我们也可以飞到其他城市。）
        // 根据题目描述第0周也可以去其他的城市，只0号城市有去目标城市的航班
        for (int j = 1; j < n; j++) {
            dp[0][j] = fly[0][j] != 0 ? day[j][0] : 0;
        }
        // 普遍位置怎么依赖，就是我们所设定的第i周必须去j号城市的最大休假天数
        for (int i = 1; i < k; i++) { // 那一周
            for (int j = 0; j < n; j++) { // 那一号城市
                int p1 = dp[i - 1][j];// i-1周在j号城市
                // 当前在j号城市，看看哪些城市可以到我
                for (int p : pass[j]) {
                    p1 = Math.max(p1, dp[i - 1][p]);
                }
                //  第i周，在第j号城市
                dp[i][j] = p1 != -1 ? p1 + day[j][i] : -1;
            }
        }
        int ans = 0;
        for (int max : dp[k - 1]) {
            ans = Math.max(ans, max);
        }
        return ans;
    }

    public static void main(String[] args) {
        int[][] flights = {{0, 1, 1}, {1, 0, 1}, {1, 1, 0}};
        int[][] days = {{7, 0, 0}, {0, 7, 0}, {0, 0, 7}};
        System.out.println(maxVacationDays(flights, days));
    }
}
