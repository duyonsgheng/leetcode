package week.week_2022_01_02;

import java.util.Arrays;

/**
 * @ClassName Code_01_BrickAll
 * @Author Duys
 * @Description
 * @Date 2022/4/1 10:05
 **/

// 给定一个正数数组arr，其中每个值代表砖块长度
// 所有砖块等高等宽，只有长度有区别
// 每一层可以用1块或者2块砖来摆
// 要求每一层的长度一样
// 要求必须使用所有的砖块
// 请问最多摆几层
// 如果无法做到，返回-1
public class Code_01_BrickAll {

    public static int maxLevels(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int n = arr.length;
        if (n == 1) {
            return 1;
        }
        Arrays.sort(arr);
        int p1 = max(arr, arr[n - 1]);
        int p2 = max(arr, arr[n - 1] + arr[0]);
        return Math.max(p1, p2);
    }

    // 试试每一层的长度固定 len，看看能不能用完所有的砖
    // 因为数组有序，我们长度是arr[n-1] 或者 arr[n-1]+arr[0]
    public static int max(int[] arr, int len) {
        int ans = 0;
        int l = 0;
        int r = arr.length - 1;
        while (l <= r) {
            if (arr[r] == len) {
                r--;
            }
            // 每一块只能用一次，所以如果是首位合并的，只能用一次，两边同时往中间走
            else if (l < r && arr[l] + arr[r] == len) {
                l++;
                r--;
                ans++;
            } else {
                return -1;
            }
        }
        return ans;
    }
}
