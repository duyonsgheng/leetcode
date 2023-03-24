package custom.code_2023_01;

import java.util.Arrays;

/**
 * @ClassName LeetCode_1471
 * @Author Duys
 * @Description
 * @Date 2023/1/31 17:36
 **/
// 1471. 数组中的 k 个最强值
public class LeetCode_1471 {
    public int[] getStrongest(int[] arr, int k) {
        Arrays.sort(arr);
        int n = arr.length;
        int m = arr[(n - 1) / 2];
        // 有序的可以使用双指针
        int i = 0, l = 0, r = n - 1;
        int[] ans = new int[k];
        while (k > 0) {
            int a = Math.abs(arr[l] - m), b = Math.abs(arr[r] - m);
            // 两个条件满足其中一个
            if (a > b || (a == b && arr[l] > arr[r])) {
                ans[i++] = arr[l++];
            } else {
                ans[i++] = arr[r--];
            }
            k--;
        }
        return ans;
    }
}
