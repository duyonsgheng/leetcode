package week.week_2022_05_04;

/**
 * @ClassName Code_04_ABCSameNumber
 * @Author Duys
 * @Description
 * @Date 2022/5/27 16:04
 **/
// 来自京东
// 4.2笔试
// 给定一个长度为3N的数组，其中最多含有0、1、2三种值
// 你可以把任何一个连续区间上的数组，全变成0、1、2中的一种
// 目的是让0、1、2三种数字的个数都是N
// 返回最小的变化次数
public class Code_04_ABCSameNumber {
    // 首先我们 不管数据状况如何，是不可能达到3次的，最多两次
    // 然后我们验证，看看能不能1次或者0次就刷玩搞定
    public static int minTimes(int[] arr) {
        int[] count = new int[3];
        for (int num : arr) {
            count[num]++;
        }
        // 说明已经都相等了，不需要刷
        if (count[0] == count[1] && count[1] == count[2]) {
            return 0;
        }
        int n = arr.length;
        int m = n / 3;
        // 如果有两种数字都是小于了m的，说明需要两次
        if (count[0] < m && count[1] < m || count[1] < m && count[2] < m || count[0] < m && count[2] < m) {
            return 2;
        }
        // 只有1种数字的次数是小于m的
        else {
            // 看看一次能不能
            return once(arr, count, m) ? 1 : 2;
        }
    }

    public static boolean once(int[] arr, int[] count, int m) {
        int less = count[0] < m ? 0 : (count[1] < m ? 1 : 2);
        int lessCount = less == 0 ? count[0] : (less == 1 ? count[1] : count[2]);
        if (count[0] > m && process(arr, 0, count[0], less, lessCount)) {
            return true;
        }
        if (count[1] > m && process(arr, 1, count[1], less, lessCount)) {
            return true;
        }
        if (count[2] > m && process(arr, 2, count[2], less, lessCount)) {
            return true;
        }
        return false;
    }

    // 窗口
    public static boolean process(int[] arr, int more, int moreCount, int less, int lessCount) {
        int[] cnt = new int[3];
        cnt[less] = lessCount;
        cnt[more] = moreCount;
        int aim = arr.length / 3;
        int l = 0, r = 0;
        while (r < arr.length || cnt[more] <= aim) {
            // cnt[more] 在窗口之外 多的数字有几个
            // 如果窗口外多的数字大于了air，说明窗口小了，需要更多的多的哪一种数字进到窗口
            if (cnt[more] > aim) {
                cnt[arr[r++]]--;
            }
            // 如果窗口外多的数字少了，说明窗口扩大了，需要收缩
            else if (cnt[more] < aim) {
                cnt[arr[l++]]++;
            }
            // 窗口之外的多的数字已经够了，
            // 就是看看少的数字和另外一种数字能不能平
            else {
                // 少的数字和窗口内数之和都小于了目标了，需要继续扩
                if (cnt[less] + r - l < aim) {
                    cnt[arr[r++]]--;
                } else if (cnt[less] + r - l > aim) {
                    cnt[arr[l++]]++;
                } else {
                    return true;
                }
            }
        }
        return false;
    }
}
