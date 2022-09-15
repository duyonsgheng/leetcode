package week.week_2022_03_04;

/**
 * @ClassName Code_04_JumpToTargets
 * @Author Duys
 * @Description
 * @Date 2022/3/28 13:11
 **/
// 来自字节
// 一开始在0位置，每一次都可以向左或者向右跳
// 第i次能向左或者向右跳严格的i步
// 请问从0到x位置，至少跳几次可以到达
// 字节考的问题其实就是这个问题
// 找到了测试链接 : https://www.luogu.com.cn/problem/CF11B
// 提交以下所有代码，把主类名改成"Main"，可以直接通过
public class Code_04_JumpToTargets {


    // 考虑奇数偶数的性质
    // 奇 奇 偶 偶 奇 奇 偶 偶
    //  如果目标与刚刚过的位置 的和相差是偶数，那么直接前面往回蹦 ，如果是奇数，那么就看看继续往前蹦，蹦到一个差值是偶数的时候，往前蹦
    //
    public static long minTimes(long x) {
        if (x == 0) {
            return 0;
        }
        long l = 0;
        long r = x;
        long m = 0;
        long near = 0;
        while (l <= r) {
            m = (l + r) / 2;
            // 一直向右跳，就是一个等差数列
            if (sum(m) >= x) {
                near = m;
                r = m - 1;
            } else {
                l = m + 1;
            }
        }
        if (sum(near) == x) {
            return near;
        }
        // 是奇数
        if (((sum(near) - x) & 1) == 1) {
            near++;
        }
        if (((sum(near) - x) & 1) == 1) {
            near++;
        }
        return near;

    }

    public static long sum(long n) {
        return (n * (n + 1) / 2);
    }
}
