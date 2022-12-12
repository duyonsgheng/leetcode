package week.week_2022_12_01;

/**
 * @ClassName Code_01_LeetCode_2376
 * @Author Duys
 * @Description
 * @Date 2022/12/8 9:25
 **/
// 2376. 统计特殊整数
public class Code_01_LeetCode_2376 {

    // 数位dp。比如当前树是 4567321
    // 总共7位数字。那么先算6位数字的所有情况
    // 然后7位数字的时候一位一位的拆开，求的答案
    public static int[] offset = {0, 1, 10, 100, 1000, 10000, 100000, 1000000, 10000000, 100000000, 1000000000};

    public static int countSpecialNumbers(int n) {
        int len = len(n);
        int ans = 0;
        for (int i = 1; i < len; i++) ans += all(i);
        int firstNumber = n / offset[len];
        // 总共7位数字，比如第一位是8，那么我就算1-7为首位数字，还剩下7位的时候，总共多少种可能
        ans += (firstNumber - 1) * small(len - 1, 9);
        // firstNumber已经使用了，并且位数减一了
        ans += process(n, len, len - 1, 1 << firstNumber);
        return ans;
    }

    public static int len(int num) {
        int len = 0;
        while (num > 0) {
            len++;
            num /= 10;
        }
        return len;
    }

    // 总共bits位，返回多少特殊的
    public static int all(int bits) {
        int ans = 9;
        int cur = 9;
        while (--bits > 0) {
            ans *= cur--;
        }
        return ans;
    }

    // 有bits位，还有几种可以选择数字
    public static int small(int bits, int candi) {
        int ans = 1;
        for (int i = 0; i < bits; i++, candi--) {
            ans *= candi;
        }
        return ans;
    }

    // num - 原始数字
    // len - 总共几位
    // rest - 还剩下几位
    // status 哪些数字已经选择了
    public static int process(int num, int len, int rest, int status) {
        if (rest == 0) {
            return 1; // 不剩下位置了，之前做的一个决定有效
        }
        // 把当前剩余位置的最高位拿出来
        int cur = (num / offset[rest]) % 10;
        // 看看小于cur并且没使用的的有几个数字
        int cnt = 0;
        for (int i = 0; i < cur; i++) {
            if ((status & (1 << i)) == 0) {
                cnt++;
            }
        }
        // 有几个不需要进行拆分的
        int ans = cnt * small(rest - 1, 9 - len + rest);
        if ((status & (1 << cur)) == 0) { // 表示当前的数字本来就是合法的才可能继续算下面的，比如 456453 再原始数字中4出现了多次，这儿就不应该继续算了
            ans += process(num, len, rest - 1, status | (1 << cur));
        }
        return ans;
    }
}
