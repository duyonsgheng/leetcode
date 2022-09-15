package duys_code.day_36;

import java.util.Arrays;

/**
 * @ClassName Code_09_MinBoatEvenNumbers
 * @Author Duys
 * @Description TODO
 * @Date 2021/12/14 13:18
 **/
public class Code_09_MinBoatEvenNumbers {

    // 来自腾讯
    // 给定一个正数数组arr，代表每个人的体重。给定一个正数limit代表船的载重，所有船都是同样的载重量
    // 每个人的体重都一定不大于船的载重
    // 要求：
    // 1, 可以1个人单独一搜船
    // 2, 一艘船如果坐2人，两个人的体重相加需要是偶数，且总体重不能超过船的载重
    // 3, 一艘船最多坐2人
    // 返回如果想所有人同时坐船，船的最小数量


    // 解法：先给这个arr排序，然后从第一个大于 limit/2的位置开始往两边走，L指针向左，R指针向右
    // 本地增加了一个限制，两个人体重相加是偶数，那么偶数肯定是奇数和奇数相加，偶数和偶数相加才是偶数
    // 参考大厂刷题班第三节，第5题
    public static int minBoat(int[] arr, int limit) {
        Arrays.sort(arr);
        int odd = 0;
        int even = 0;
        for (int i = 0; i < arr.length; i++) {
            if ((arr[i] & 1) == 0) {
                even++;
            } else {
                odd++;
            }
        }
        int[] odds = new int[odd];
        int[] evens = new int[even];
        for (int i = arr.length - 1; i >= 0; i--) {
            if ((arr[i] & 1) == 0) {
                evens[--even] = arr[i];
            } else {
                odds[--odd] = arr[i];
            }
        }
        return min(odds, limit) + min(evens, limit);
    }

    // 算法原型
    //解法：先给这个arr排序，然后从第一个大于 limit/2的位置开始往两边走，L指针向左，R指针向右
    public static int min(int[] arr, int limit) {
        if (arr == null || arr.length <= 0) {
            return 0;
        }
        int n = arr.length;
        // 有一个人的体重大于船的载重，搞不定
        if (arr[n - 1] > limit) {
            return -1;
        }
        int lessR = -1;
        for (int i = 0; i < n; i++) {
            if (arr[i] <= limit / 2) {
                lessR = i;
                break;
            }
        }
        // 都是大于limit/2 的。每一个人一艘船
        if (lessR == -1) {
            return n;
        }
        int l = lessR;
        int r = lessR + 1;
        int noUsed = 0;
        // 两个指针开始滑动
        while (l >= 0) {
            int ans = 0;
            // 看看两个人一艘船
            // l左边的都是小于自己的，
            // r右边的都是大于自己的
            while (r < n && arr[l] + arr[r] <= limit) {
                r++;
                ans++;
            }
            if (ans == 0) {
                noUsed++;
                l--;
            } else {
                // 看看之前配对了几个人，因为两边都是有序的
                l = Math.max(-1, l - ans);
            }
        }
        int all = lessR + 1;
        int used = all - noUsed;
        int moreUnsolved = (n - all) - used;
        return used + ((noUsed + 1) >> 1) + moreUnsolved;
    }
}
