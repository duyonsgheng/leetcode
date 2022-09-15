package week.week_2021_11_04;

import java.util.Arrays;

/**
 * @ClassName Code_03_StartToEndBinaryOneTarget
 * @Author Duys
 * @Description 数位DP 本来就很难的题
 * @Date 2022/1/25 17:54
 **/
public class Code_03_StartToEndBinaryOneTarget {
    // 题目：
    // 限制 0<=start <= end，0<=target<=64
    // [start,end]上有多少个数二进制位中1的个数是target

    // 这个比较暴力-为了验证
    public static long sum1(long start, long end, int target) {
        if (start < 0 || end < 0 || start > end || target < 0) {
            return -1;
        }
        long ans = 0;
        for (long i = start; i <= end; i++) {
            if (bitOne(i) == target) {
                ans++;
            }
        }
        return ans;
    }

    public static int bitOne(long num) {
        int bits = 0;
        for (int i = 0; i < 64; i++) {
            if ((num & (1L << i)) != 0) {
                bits++;
            }
        }
        return bits;
    }

    // 正式的方法
    public static long sum2(long start, long end, int target) {
        if (start < 0 || end < 0 || start > end || target < 0) {
            return -1;
        }
        if (start == 0 && end == 0) {
            return target == 0 ? 1 : 0;
        }
        // 寻找end这个数的最高位1在哪里
        int endHig = 62;
        while ((end & (1L << endHig)) == 0) {
            endHig--;
        }
        // 这里直接就是 0到end
        if (start == 0) {
            return process2(endHig, 0, target, end);
        }
        // 这里就是0到end -  0到start-1
        else {
            start--;
            int startHig = 62;
            while ((start & (1L << startHig)) == 0) {
                startHig--;
            }
            return process2(endHig, 0, target, end) - process2(startHig, 0, target, start);
        }
    }

    // index: 当前来到哪一个位置上做决定，index一定到num的最高位为1的地方，
    // less: 之前做的决定是不是已经比num大了
    // rest: 还剩下几个1需要出现
    // 递归含义：[i....index+1]已经做了决定了，还剩下[index...0]没做决定
    // 在[i...index+1]上做的决定是不是已经比num大了。
    // less = 1 就是表示还没有大于了num
    // less =0说明[0..index+1]做的决定一定是==num的前缀状态。
    public static long process2(int index, int less, int rest, long num) {
        if (rest > index + 1) {
            return 0;
        }
        if (rest == 0) {
            return 1;
        }
        // 还有1需要去消耗
        // 位数也够用
        // less = 1，说明之前做的决定已经小于了num了
        if (less == 1) {
            if (rest == index + 1) {
                return 1;
            } else {
                // 说明后面剩余的位置数量是 > 需要消耗的1的数量rest的
                // 有些位置填0，有些位置填1.之前已经都是小于num的，说明从左往右尝试就可以
                return process2(index - 1, 1, rest, num) + process2(index - 1, 1, rest - 1, num);
            }
        } else {
            // less = 0，说明之前就已经相等了（相等是等于num的前缀状态），需要小心的是，不能大于num
            if (rest == index + 1) {
                // 这里的意思就是 [i...index+1]上是相同的，但是呢还剩下index+1个位置和rest个1需要去消耗，而且rest=index+1，意思就是必须要是剩下的位置全部是1
                // 但是如果num的index位置上本来就是0，那么就没有意义了。
                return (num & (1 << index)) == 0 ? 0 : process2(index - 1, 0, rest - 1, num);
            } else {
                // 某些位置填1，某些位置填0
                if ((num & (1 << index)) == 0) {
                    // num的index位置上是0，这儿如果填1，后面一定是大于的，所以这儿继续填0，后面的保持等于往下传
                    return process2(index - 1, 0, rest, num);
                } else {
                    // num的当前位置本来就是1
                    // 可以填1，可以填0
                    return process2(index - 1, 0, rest - 1, num) +
                            // 当前位置上本来是1，但是我这儿填0，就表示后面过程肯定是小于num对应的前缀的。
                            process2(index - 1, 1, rest, num);
                }
            }
        }
    }


    // 对上面的方法进行优化
    public static long num3(long start, long end, int target) {
        if (start < 0 || end < 0 || start > end || target < 0) {
            return -1;
        }
        if (start == 0 && end == 0) {
            return target == 0 ? 1 : 0;
        }
        int endHig = 62;
        while ((end & (1 << endHig)) == 0) {
            endHig--;
        }
        // 对上面的2方法进行dp
        long[][][] dp = new long[endHig + 1][2][target + 1];
        for (int i = 0; i <= endHig; i++) {
            Arrays.fill(dp[i][0], -1);
            Arrays.fill(dp[i][1], -1);
        }
        // 0到end
        long ans = process3(endHig, 0, target, end, dp);
        if (start == 0) {
            return ans;
        } else {
            start--;
            int startHig = 62;
            while (startHig >= 0 && (start & (1 << startHig)) == 0) {
                startHig--;
            }
            long[][][] dp2 = new long[startHig + 1][2][target + 1];
            for (int i = 0; i <= startHig; i++) {
                Arrays.fill(dp2[i][0], -1);
                Arrays.fill(dp2[i][1], -1);
            }
            long ans2 = process3(startHig, 0, target, start, dp2);
            return ans - ans2;
        }
    }

    public static long process3(int index, int less, int rest, long num, long[][][] dp) {
        if (rest > index + 1) {
            return 0;
        }
        if (rest == 0) {
            return 1;
        }
        if (dp[index][less][rest] != -1) {
            return dp[index][less][rest];
        }
        // 还有1需要去消耗
        // 位数也够用
        // less = 1，说明之前做的决定已经小于了num了
        long ans = 0;
        if (less == 1) {
            if (rest == index + 1) {
                ans = 1;
            } else {
                // 说明后面剩余的位置数量是 > 需要消耗的1的数量rest的
                // 有些位置填0，有些位置填1.之前已经都是小于num的，说明从左往右尝试就可以
                ans = process3(index - 1, 1, rest, num, dp) + process3(index - 1, 1, rest - 1, num, dp);
            }
        } else {
            // less = 0，说明之前就已经相等了（相等是等于num的前缀状态），需要小心的是，不能大于num
            if (rest == index + 1) {
                // 这里的意思就是 [i...index+1]上是相同的，但是呢还剩下index+1个位置和rest个1需要去消耗，而且rest=index+1，意思就是必须要是剩下的位置全部是1
                // 但是如果num的index位置上本来就是0，那么就没有意义了。
                ans = (num & (1 << index)) == 0 ? 0 : process3(index - 1, 0, rest - 1, num, dp);
            } else {
                // 某些位置填1，某些位置填0
                if ((num & (1 << index)) == 0) {
                    // num的index位置上是0，这儿如果填1，后面一定是大于的，所以这儿继续填0，后面的保持等于往下传
                    ans = process3(index - 1, 0, rest, num, dp);
                } else {
                    // num的当前位置本来就是1
                    // 可以填1，可以填0
                    ans = process3(index - 1, 0, rest - 1, num, dp) +
                            // 当前位置上本来是1，但是我这儿填0，就表示后面过程肯定是小于num对应的前缀的。
                            process3(index - 1, 1, rest, num, dp);
                }
            }
        }
        dp[index][less][rest] = ans;
        return ans;
    }

}
