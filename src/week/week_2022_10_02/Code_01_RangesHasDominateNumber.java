package week.week_2022_10_02;

/**
 * @ClassName Code_01_RangesHasDominateNumber
 * @Author Duys
 * @Description
 * @Date 2022/10/13 13:00
 **/
// 来自小红书
// 小A认为如果在数组中有一个数出现了至少k次
// 且这个数是该数组的众数，即出现次数最多的数之一
// 那么这个数组被该数所支配
// 显然当k比较大的时候，有些数组不被任何数所支配
// 现在小A拥有一个长度为n的数组，她想知道内部有多少个区间是被某个数支配的
// 2 <= k <= n <= 100000
// 1 <= 数组的值 <= n
public class Code_01_RangesHasDominateNumber {
    // 思路，整个数组所有的区间可以算出来。比如 长度为 5的数组
    // 所有的区间
    //0-0 0-1 0-2 0-3 0-4
    //1-1 1-2 1-3 1-4
    // 一个等差数列
    // 然后窗口看看哪些不符合的

    public static int dominates(int[] arr, int k) {
        int n = arr.length;
        int all = n * (n - 1) / 2;
        int not = 0;
        int[] cnt = new int[n + 1]; // 词频统计
        // 窗口操作
        for (int l = 0, r = 0; l < n; l++) {
            // 看看窗口能扩到那儿
            while (r < n && cnt[arr[r + 1]] + 1 < k) {
                cnt[arr[r++]]++;// 词频加1
            }
            not += r - l;// 这个区间是不满足的

            // 我的l即将向右，也就说窗口要缩进了
            cnt[arr[l]]--;
        }
        return all - not;
    }
}
