package duys.class_08_24;

/**
 * @ClassName ArrayAndKQuestion_03
 * @Author Duys
 * @Description
 * @Date 2021/8/25 11:13
 **/
public class ArrayAndKQuestion_03 {
    /**
     * 题目：
     * 给定一个整数组成的无序数组arr，值可能正、可能负、可能0
     * 给定一个整数值K
     * 找到arr的所有子数组里，哪个子数组的累加和<=K，并且是长度最大的
     * 返回其长度
     */
    /**
     * 题难：因为难在有可能性需要舍弃的
     * 1.把每一个位置开始的子数组，最小的前缀和求出来，保存在minSum数组中，然后把对应的位置放在minSumEnd数组中。
     * 例如：     [1, 3, 4, 5,-4, 7,-3]
     * minSum:   [0,-1,-4,1,-4, 4,-3]
     * minSumEnd:[2, 2, 2, 4,4, 6, 6]
     * 从后往前推，因为后面的都已经最小了，那么前面的只需要看看是否需要后面的最小来推自己的最小
     */
    public static int getMaxLength(int[] arr, int k) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int[] minSum = new int[arr.length];
        int[] minSumEnd = new int[arr.length];
        minSum[arr.length - 1] = arr[arr.length - 1];
        minSumEnd[arr.length - 1] = arr.length - 1;
        for (int i = arr.length - 2; i >= 0; i--) {
            if (minSum[i + 1] < 0) {
                minSum[i] = arr[i] + minSum[i + 1];
                minSumEnd[i] = minSumEnd[i + 1];
            } else {
                minSum[i] = arr[i];
                minSumEnd[i] = i;
            }
        }
        //
        int end = 0; // 表示的下一个扩不进来的开头位置
        int sum = 0;
        int len = 0;// 全局的最大长度
        for (int i = 0; i < arr.length; i++) {
            // while结束后：如果当前满足情况的 数组是i....end-1 那么，
            //看看 end-i能不能把之前算出来的答案更新，如果能更新就会更新，不能更新也没影响
            while (end < arr.length && sum + minSum[end] <= k) {
                sum += minSum[end];
                // 下一个区域的开头
                end = minSumEnd[end] + 1;
            }
            len = Math.max(len, end - i);
            // 如果当前sum+minSum[end]了，那么就会从这儿开始重新找合适的end位置。如果i到end之间还有数，那么就是缩小之前的区域，
            // 然后尝试把新的区域能否合并进来，从而达到把len推高的结果
            if (end > i) { // 如果还有，看看能不能通过减少当前的区域，来把还剩下的区域扩展进来
                sum -= arr[i];
            } else { // 这时候说明end ==i的。那么接下来i++，那么end也需要往后走
                end = i + 1;
            }
        }
        return len;
    }
}
