package duys_code.day_14;

import java.util.TreeSet;

/**
 * @ClassName Code_02_MinKByArray
 * @Author Duys
 * @Description
 * @Date 2021/10/25 13:06
 **/
public class Code_02_MinKByArray {
    /**
     * 题意：
     * 请返回arr中，求子数组的累加和，是<=K的并且是最大的
     * 返回这个最大的累加和
     */
    /**
     * 思考：我们要求子数组的累加和。是<=K 的。那么意思就是，找一个前缀和是大于K 切离K最近的。那么这个问题就简化到 前缀和 和 有序表来做了
     */
    public static int getMaxLessOrEqualK(int[] arr, int K) {
        if (arr == null || K < 0) {
            return Integer.MIN_VALUE;
        }
        TreeSet<Integer> set = new TreeSet<>();
        set.add(0);// 添加一个0，垫底。因为当我们一个数都没有的时候。，累加和就是0
        int sum = 0;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
            Integer ceiling = set.ceiling(sum - K);
            if (ceiling != null) {
                max = Math.max(max, ceiling);
            }
            set.add(sum);
        }
        return max;
    }
}
