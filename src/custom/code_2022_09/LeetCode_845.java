package custom.code_2022_09;

/**
 * @ClassName LeetCode_845
 * @Author Duys
 * @Description
 * @Date 2022/9/29 14:40
 **/
// 845. 数组中的最长山脉
public class LeetCode_845 {
    // arr = [2,1,4,7,3,2,5]
    // 左边找上升坡度
    // 右边找上升坡度
    public int longestMountain(int[] arr) {
        if (arr == null || arr.length <= 0) {
            return 0;
        }
        int n = arr.length;
        // 左边上升的
        int[] left = new int[n];
        for (int i = 1; i < n; i++) {
            if (arr[i - 1] < arr[i]) {
                left[i] = left[i - 1] + 1;
            } else left[i] = 0;

        }
        // 右边上升的
        int[] right = new int[n];
        for (int i = n - 2; i >= 0; i--) {
            if (arr[i] > arr[i + 1]) {
                right[i] = right[i + 1] + 1;
            } else right[i] = 0;
        }
        int ans = 0;
        // 当来一个一个位置，既有左边上升，又有右边上升的，那么就可以结算答案
        for (int i = 0; i < n; i++) {
            if (left[i] > 0 && right[i] > 0) {
                ans = Math.max(left[i] + right[i] + 1, ans);
            }
        }
        return ans;
    }
}
