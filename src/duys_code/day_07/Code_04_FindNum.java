package duys_code.day_07;

import java.util.HashSet;

/**
 * @ClassName Code_04_FindNum
 * @Author Duys
 * @Description
 * @Date 2021/9/28 16:03
 **/
public class Code_04_FindNum {
    /**
     * 题意：
     * 给定一个有序数组arr，其中值可能为正、负、0
     * 返回arr中每个数都平方之后不同的结果有多少种？
     * <p>
     * 给定一个数组arr，先递减然后递增，
     * 返回arr中有多少个不同的数字？
     */

    // 时间复杂度O(N) ，空间复杂度O(N)
    public static int diff1(int[] arr) {
        if (arr == null || arr.length < 1) {
            return 0;
        }
        HashSet<Integer> set = new HashSet<>();
        for (int num : arr) {
            set.add(num * num);
        }
        return set.size();
    }

    // 时间复杂度O(N) ，空间复杂度O(1)，双指针，而且边界检查
    public static int diff2(int[] arr) {
        if (arr == null || arr.length < 1) {
            return 0;
        }
        int N = arr.length;
        int count = 0;
        // 整体思路是谁大谁移动，相等就都移动，谁大得时候，遍历一下看看当前大得这个数是不是连续位置都是相同得这个数，边界检查
        int L = 0; // 左指针
        int R = N - 1; // 右指针。
        int leftAbs = 0;
        int rightAbs = 0;
        while (L <= R) {
            count++;
            leftAbs = Math.abs(arr[L]);
            rightAbs = Math.abs(arr[R]);
            if (leftAbs < rightAbs) {
                // 多个arr[R]连续存在
                while (R >= 0 && Math.abs(arr[R]) == rightAbs) {
                    R--;
                }
            } else if (leftAbs > rightAbs) {
                // 多个arr[L]连续存在
                while (L < N && Math.abs(arr[L]) == leftAbs) {
                    L++;
                }
            }
            // 相等得时候同时移动
            else {
                // 多个arr[R]连续存在
                while (R >= 0 && Math.abs(arr[R]) == rightAbs) {
                    R--;
                }
                // 多个arr[L]连续存在
                while (L < N && Math.abs(arr[L]) == leftAbs) {
                    L++;
                }
            }
        }
        return count;
    }

}
