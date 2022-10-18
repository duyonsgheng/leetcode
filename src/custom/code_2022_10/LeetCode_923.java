package custom.code_2022_10;

import java.util.Arrays;

/**
 * @ClassName LeetCode_923
 * @Author Duys
 * @Description
 * @Date 2022/10/18 13:04
 **/
// 923. 三数之和的多种可能
public class LeetCode_923 {
    int mod = 1_000_000_007;

    // 三指针
    public int threeSumMulti(int[] arr, int target) {
        long ans = 0;
        Arrays.sort(arr);
        int n = arr.length;
        for (int i = 0; i < n; i++) {
            int t = target - arr[i];
            int j = i + 1;
            int k = n - 1;
            while (j < k) {
                // 转化为二元组问题
                if (arr[j] + arr[k] < t) {
                    j++; // 说明j小了，继续往右找
                } else if (arr[j] + arr[k] > t) {
                    k--;// 说明大了，k需要缩小
                } else if (arr[j] != arr[k]) {
                    int left = 1; // 和 j位置相等的几个数
                    int right = 1; // 和 k位置相等的几个数
                    // 找到和j位置相等的区间
                    while (j + 1 < k && arr[j] == arr[j + 1]) {
                        left++;
                        j++;
                    }
                    // 找到和k位置相等的区间
                    while (k - 1 > j && arr[k] == arr[k - 1]) {
                        right++;
                        k--;
                    }
                    ans += left * right;
                    ans %= mod;
                    j++; // 下一个位置继续
                    k--; // 前一个位置继续
                } else {
                    // 这个区间全部相等，就一个等差数列求和公式啊
                    ans += (k - j + 1) * (k - j) / 2;
                    ans %= mod;
                    break;
                }
            }
        }
        return (int) ans;
    }
}
