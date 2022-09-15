package duys_code.day_40;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName Code_01_Split
 * @Author Duys
 * @Description
 * @Date 2021/12/22 10:28
 **/
public class Code_01_Split {
    // 腾讯
    // 分裂问题
    // 一个数n，可以分裂成一个数组[n/2, n%2, n/2]
    // 这个数组中哪个数不是1或者0，就继续分裂下去
    // 比如 n = 5，一开始分裂成[2, 1, 2]
    // [2, 1, 2]这个数组中不是1或者0的数，会继续分裂下去，比如两个2就继续分裂
    // [2, 1, 2] -> [1, 0, 1, 1, 1, 0, 1]
    // 那么我们说，5最后分裂成[1, 0, 1, 1, 1, 0, 1]
    // 每一个数都可以这么分裂，在最终分裂的数组中，假设下标从1开始
    // 给定三个数n、l、r，返回n的最终分裂数组里[l,r]范围上有几个1
    // n <= 2 ^ 50，n是long类型
    // r - l <= 50000，l和r是int类型
    // 我们的课加个码:
    // n是long类型随意多大都行
    // l和r也是long类型随意多大都行，但要保证l<=r

    // 首先看看n分裂结束有多少位，然后看看 l到r是在哪一个区域，左 中 右
    // 2O(n/2) + log(n) -> 根据master公式得出，整个流程趋近于 O(n)
    public static long nums1(long num, long l, long r) {
        if (num == 0 || num == 1) {
            return num == 1 ? 1 : 0;
        }
        long half = size(num / 2);
        // 分区域来
        // 如果我的l都是大于half的，说明没有左边的
        long left = l > half ? 0 : nums1(num / 2, l, Math.min(half, r));
        // 如果l到r包含了中间，如果包含了就需要算上
        long mid = (l > half + 1 || r < half + 1) ? 0 : (num & 1);
        // 右边区域的较特殊一点，需要下标换算
        // 无论左边还是右边，在递归的过程中，下标都是1到half，然后实际是 left：1 到 half ， mid： 1 ，right：half+2 到 half*2+1
        // 把右边的换算成下标
        long right = r > half + 1 ? nums1(num / 2, Math.max(l - half - 1, 1), r - half - 1) : 0;
        return left + mid + right;
    }

    // 求num有多少位
    // num/2 有多少位，那么num就是 num/2 的位数 * 2 +1
    public static long size(long num) {
        if (num == 0 || num == 1) {
            return num == 1 ? 1 : 0;
        }
        long half = size(num / 2);
        return half * 2 + 1;
    }

    // 这是一个log(n)的解答
    // 求解的过程就跟 线段树的query流程很像，如果包含了某一个区域，整个区域就直接返回，意思就是左右两条线往下查，查边界，就是2*O(log(n))
    public static long nums2(long num, long l, long r) {
        Map<Long, Long> allMap = new HashMap<>();
        return dp(num, l, r, allMap);
    }

    public static long dp(long num, long l, long r, Map<Long, Long> allMap) {
        if (num == 0 || num == 1) {
            return num == 0 ? 0 : 1;
        }
        long half = size(num / 2);
        long all = (half << 1) + 1;
        long mid = num & 1;
        // 包含了整个区域
        if (l == 1 && r >= all) {
            if (allMap.containsKey(num)) {
                return allMap.get(num);
            } else {
                long count = dp(num / 2, 1, half, allMap);
                long curAll = count * 2 + mid;
                allMap.put(num, curAll);
                return curAll;
            }
        } else {
            mid = (l > half + 1 || r < half + 1) ? 0 : half;
            long left = l > half ? 0 : dp(num / 2, l, Math.min(half, r), allMap);
            long right = r > half + 1 ? dp(num / 2, Math.max(l - half - 1, 1), r - half - 1, allMap) : 0;
            return left + mid + right;
        }
    }
}
