package week.week_2022_03_03;

/**
 * @ClassName Code_03_MaxSumOnRever
 * @Author Duys
 * @Description
 * @Date 2022/3/17 15:39
 **/
public class Code_03_MaxSumOnReverse {
// 来自美团
// 最大子段和是
// 一个经典问题，即对于一个数组找出其和最大的子数组。
// 现在允许你在求解该问题之前翻转这个数組的连续一段
// 如翻转(1,2,3,4,5,6)的第三个到第五个元素組成的子数组得到的是(1,2,5,4,3,6)，
// 则翻转后该数组的最大子段和最大能达到多少？
// 来自字节
// 几乎一样的题，来自字节笔试第4题
// 给定两个数組values和numbers，
// values[i]表示i号宝石的单品价值
// numbers[i]表示i号宝石的数量
// i号宝石的总价值 = values[i] * numbers[i]
// 如果有一种魔法，可以翻转任何区间L...R的宝石，也就是改变L..R的宝石排列，变成逆序的
// 求在允许用一次魔法的情况下，任取一段连续区间，能达到的最大价值
// 这两个问法解法都几乎一样，区别无非是:
// 美团的: 可进行一次翻转情况下，子数组最大累加和
// 字节的: 可进行一次翻转情况下，子数组最大价值和

    public static int maxSum(int[] arr) {
        int n = arr.length;
        int[] prefix = new int[n];
        prefix[n - 1] = arr[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            // 当前位置 加上后面最好的后缀和，
            prefix[i] = arr[i] + Math.max(0, prefix[i + 1]);
        }
        int ans = prefix[0];
        int suffix = arr[0];
        int maxSuffix = suffix;
        for (int i = 0; i < n; i++) {
            ans = Math.max(ans, maxSuffix + prefix[i]);
            suffix = arr[i] + Math.max(0, suffix);
            maxSuffix = Math.max(suffix, maxSuffix);
        }
        ans = Math.max(ans, maxSuffix);
        return ans;
    }
}
