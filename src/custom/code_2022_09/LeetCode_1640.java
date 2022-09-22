package custom.code_2022_09;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName LeetCode_1640
 * @Author Duys
 * @Description
 * @Date 2022/9/22 13:20
 **/
// 1640. 能否连接形成数组
public class LeetCode_1640 {
    public static void main(String[] args) {
        int[] arr = {91, 4, 64, 78};
        int[][] p = {{78}, {4, 64}, {91}};
        System.out.println(canFormArray(arr, p));
    }

    public static boolean canFormArray(int[] arr, int[][] pieces) {
        if (arr == null || arr.length <= 0) {
            return true;
        }
        if (pieces == null || pieces.length <= 0 || pieces[0] == null || pieces[0].length <= 0) {
            return false;
        }
        Map<Integer, int[]> map = new HashMap<>();
        for (int[] nums : pieces) {
            map.put(nums[0], nums);
        }
        int n = arr.length;
        int r = 0;
        while (r < n) {
            int first = arr[r];
            if (!map.containsKey(first)) {
                return false;
            }
            int i1 = r;
            int[] ints = map.get(first);
            int i2 = 0;
            while (i1 < n && i2 < ints.length && arr[i1] == ints[i2]) {
                i2++;
                i1++;
            }
            if (i2 != ints.length) {
                return false;
            }
            r = i1;
        }
        return true;
    }
}
