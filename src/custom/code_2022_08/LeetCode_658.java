package custom.code_2022_08;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName LeetCode_658
 * @Author Duys
 * @Description
 * @Date 2022/8/25 13:50
 **/
// 658. 找到 K 个最接近的元素
public class LeetCode_658 {
    // 首先二分，找到与x插值最小的位置
    // 然后双指针，往前往后
    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        List<Integer> ans = new ArrayList<>();
        if (arr == null || arr.length < k) {
            return ans;
        }
        // right 两边是小于的大于x的
        int right = find(arr, x);
        int left = right - 1;
        while (k-- > 0) {
            if (left < 0) {
                right++;
            } else if (right >= arr.length) {
                left--;
            } else if (x - arr[left] <= arr[right] - x) {
                left--;
            } else {
                right++;
            }
        }
        for (int i = left + 1; i < right; i++) {
            ans.add(arr[i]);
        }
        return ans;
    }

    // 分成两组
    public static int find(int[] arr, int x) {
        int l = 0;
        int r = arr.length - 1;
        while (l < r) {
            int m = (l + r) / 2;
            if (arr[m] >= x) {
                r = m;
            } else {
                l = m + 1;
            }
        }
        return l;
    }
}
