package custom.code_2023_06;

/**
 * @ClassName LeetCode_1894
 * @Author Duys
 * @Description
 * @Date 2023/6/19 15:19
 **/
// 1894. 找到需要补充粉笔的学生编号
public class LeetCode_1894 {
    public static int chalkReplacer(int[] chalk, int k) {
        int n = chalk.length;
        long total = 0;
        for (int num : chalk) {
            total += num;
        }
        k %= total;
        int res = -1;
        for (int i = 0; i < n; ++i) {
            if (chalk[i] > k) {
                res = i;
                break;
            }
            k -= chalk[i];
        }
        return res;
    }

    public static int chalkReplacer2(int[] chalk, int k) {
        int n = chalk.length;
        if (chalk[0] > k) {
            return 0;
        }
        for (int i = 0; i < n; i++) {
            chalk[i] += chalk[i - 1];
            if (chalk[i] > k) {
                return i;
            }
        }
        k %= chalk[n - 1];
        int l = 0, r = n - 1, m = 0;
        while (l < r) {
            m = (r - l) / 2 + l;
            if (chalk[m] <= k) {
                l = m + 1;
            } else {
                r = m;
            }
        }
        return l;
    }

    public static void main(String[] args) {
        System.out.println(chalkReplacer(new int[]{5, 1, 5}, 22));
        System.out.println(chalkReplacer(new int[]{3, 4, 1, 2}, 25));
    }
}
