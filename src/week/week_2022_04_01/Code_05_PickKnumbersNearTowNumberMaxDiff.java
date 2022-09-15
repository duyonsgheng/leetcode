package week.week_2022_04_01;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * @ClassName Code_05_PickKnumbersNearTowNumberMaxDiff
 * @Author Duys
 * @Description
 * @Date 2022/4/7 13:17
 **/

// 小红书第二题：
// 薯队长最近在参加了一个活动，主办方提供了N个礼物以供挑选，
// 每个礼物有一个价值，范围在0 ~ 10^9之间，
// 薯队长可以从中挑选k个礼物
// 返回：其中价值最接近的两件礼物之间相差值尽可能大的结果
public class Code_05_PickKnumbersNearTowNumberMaxDiff {
    // 看到这个数据量，首先想到的是二分
    // 最大差值是啥，就是最大值-最小值

    public static int maxNear(int[] arr, int k) {
        if (arr == null || arr.length == 0 || arr.length < k) {
            return -1;
        }
        Arrays.sort(arr);
        int n = arr.length;
        int l = 0;
        int m = 0;
        int r = arr[n - 1] - arr[0];
        int ans = 0;
        while (l <= r) {
            m = (l + r) >> 1;
            if (isOk(arr, k, m)) {
                ans = m;
                l = m + 1;
            } else {
                r = m - 1;
            }
        }
        return ans;
    }

    public static boolean isOk(int[] arr, int k, int limit) {
        int last = arr[0];
        int pk = 1;
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] - last >= limit) {
                pk++;
                last = arr[i];
            }
        }
        return pk >= k;
    }
}
