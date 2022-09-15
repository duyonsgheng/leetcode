package week.week_2022_04_01;

import java.util.Arrays;

/**
 * @ClassName Code_01_FourNumbersMinusOne
 * @Author Duys
 * @Description
 * @Date 2022/4/7 9:13
 **/

// 来自阿里笔试
// 牛牛今年上幼儿园了，老师叫他学习减法
// 老师给了他5个数字，他每次操作可以选择其中的4个数字减1
// 减一之后的数字不能小于0，因为幼儿园的牛牛还没有接触过负数
// 现在牛牛想知道，自己最多可以进行多少次这样的操作
// 扩展问题来自leetcode 2141，掌握了这个题原始问题就非常简单了
// leetcode测试链接 : https://leetcode.com/problems/maximum-running-time-of-n-computers/
public class Code_01_FourNumbersMinusOne {

    // 原始问题：问给大楼供电，最长能坚持多少分钟

    /**
     * 分析：
     * 1.最长的供电时间不会超过整个电池数组的累加和
     * 2.然后在1到sum上进行二分
     * 3.比如当前是10分钟，7栋楼，电池数组里面有大于10的可以供1栋楼，去掉电池，楼栋数-1
     * 4.剩下的不满足10分钟电池看看剩下的电池和总数是不是楼栋*10，如果达到要求，一定能满足
     * 4.1 比如 5 6 7 7 8 8 9
     * 让6电量的电池先去2号楼撑住1分钟，把剩下的5个电量给1号楼，第一个7电量电池去2号楼，第二个7电量的电池先去3号楼顶住5分钟，2分钟给2号楼。。。。依次这样拆分，组合
     */

    public static long maxRunTime(int n, int[] batteries) {
        // 1.排序
        Arrays.sort(batteries);
        int size = batteries.length;
        long[] sums = new long[size];
        sums[0] = batteries[0];
        for (int i = 1; i < size; i++) {
            sums[i] = sums[i - 1] + batteries[i];
        }
        long l = 0;
        long m = 0;
        long r = sums[size - 1];
        long ans = -1;
        while (l <= r) {
            m = l + ((r - l) >> 1);
            if (isOk(batteries, sums, m, n)) {
                ans = m;
                // 看看能不能撑住更多的时间
                l = m + 1;
            } else {
                r = m - 1;
            }
        }
        return ans;
    }

    // time。就是我们当前最少能供多少分钟，二分答案中的答案
    // num 是楼数
    public static boolean isOk(int[] arr, long[] sums, long time, int num) {
        int l = 0;
        int m = 0;
        int r = arr.length - 1;
        int left = arr.length;
        // >= time 最左的位置
        while (l <= r) {
            m = l + ((r - l) >> 1);
            if (arr[m] >= time) {
                r = m - 1;
                left = m;
            } else {
                l = m + 1;
            }
        }
        // 减去能单独供电的电池电量有多少个
        num -= arr.length - left;
        // 剩下多少电量总和
        long rest = left == 0 ? 0 : sums[left - 1];
        // 看看 需要的电量和剩下的总电量是否满足
        return time * num <= rest;
    }

    public static void main(String[] args) {
        System.out.println(maxRunTime(2, new int[]{3, 3, 3}));
    }
}
