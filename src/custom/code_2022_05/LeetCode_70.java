package custom.code_2022_05;

/**
 * @ClassName LeetCode_70
 * @Author Duys
 * @Description
 * @Date 2022/5/10 13:05
 **/
// 假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
//每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
public class LeetCode_70 {
    public static int climbStairs(int n) {
        if (n <= 0) {
            return -1;
        }
        if (n == 1) {
            return 1;
        }
        if (n == 2) {
            return 2;
        }
        int ans = 0;
        int pre1 = 1;
        int pre2 = 2;
        for (int i = 3; i <= n; i++) {
            ans += (pre1 + pre2);
            pre1 = pre2;
            pre2 = ans;
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(climbStairs(1));
    }
}
