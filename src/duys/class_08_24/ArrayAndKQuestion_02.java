package duys.class_08_24;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName ArrayAndKQuestion_02
 * @Author Duys
 * @Description
 * @Date 2021/8/25 11:01
 **/
public class ArrayAndKQuestion_02 {
    /**
     * 题目：
     * 给定一个整数组成的无序数组arr，值可能正、可能负、可能0
     * 给定一个整数值K
     * 找到arr的所有子数组里，哪个子数组的累加和等于K，并且是长度最大的
     * 返回其长度
     */
    /**
     * 此题就没有单调性了，因为可负数。所以滑动窗口就不能用了
     * 前缀和最早出现的位置，0 出现在-1位置
     */
    public static int getMaxLength(int[] arr, int k) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        Map<Integer, Integer> indexMap = new HashMap<>();
        indexMap.put(0, -1);
        int len = 0;
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
            // 比如K =10 ，那么当前前缀和15 ，那么看看有没有出现5的前缀和在什么位置
            if (indexMap.containsKey(sum - k)) {
                len = Math.max(len, i - indexMap.get(sum - k));
            }
            if (!indexMap.containsKey(sum)) {
                indexMap.put(sum, i);
            }
        }
        return len;
    }
}
