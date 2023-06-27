package custom.code_2023_05;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * @ClassName LeetCode_1846
 * @Author Duys
 * @Description
 * @Date 2023/5/30 13:19
 **/
// 1846. 减小和重新排列数组后的最大元素
public class LeetCode_1846 {
    public int maximumElementAfterDecrementingAndRearranging(int[] arr) {
        Arrays.sort(arr);
        int n = arr.length;
        arr[0] = 1;
        for (int i = 1; i < n; i++) {
            arr[i] = Math.min(arr[i], arr[i - 1] + 1);
        }
        return arr[n - 1];
    }
}
