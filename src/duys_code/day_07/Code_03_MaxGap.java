package duys_code.day_07;

import java.util.Arrays;

/**
 * @ClassName Code_03_MaxGap
 * @Author Duys
 * @Description
 * @Date 2021/9/28 11:01
 **/
public class Code_03_MaxGap {
    /**
     * 给定一个数组arr，
     * 返回如果排序之后，相邻两数的最大差值
     * eg： [6,9,1,3]返回 排序后 [1,3,6,9] 最大的差值是3
     * 如果先排序，那么就是n*logN，本题要求O(N)。所以这里要使用不基于比较的排序，借用桶排序的思想
     * 要求：时间复杂度O(N)
     * 假设答案法：
     * 1.加入当前有9个数，那么准备10个桶，0-9 在 0号桶 ，10 - 19在1号桶 ...... 90-99在9号桶
     * 那么每一个桶内的最大值和最小值的差距就不如 1号桶的最小 减去0号桶的最大。所以答案就是 不同的桶的最小和另外的桶最大的差值
     * 因为桶内部的最小和最大的差值不超过桶的限定，桶限定是0-9，10-19... 是10
     */
    // O(N*logN)
    public static int maxGap1(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }
        // O(N*logN)
        Arrays.sort(arr);
        int N = arr.length;
        // 保证窗口内始终两个数
        int L = 0;
        int R = 1;
        int max = Integer.MIN_VALUE;
        while (R <= N - 1) {
            max = Math.max(arr[R++] - arr[L++], max);
        }
        return max;
    }

    // O(N) - 思想很重要，思想来自，我们使用基于比较的排序，最好情况下，时间复杂度是 N*logN，如果我们想达到 O(N) 。
    // 首先想到的是不基于比较的排序。桶排序。但是我们又不能去实际排序。可以借用思想
    public static int maxGap2(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }
        // 原数组长度
        int len = arr.length;
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < len; i++) {
            min = Math.min(min, arr[i]);
            max = Math.max(max, arr[i]);
        }
        // 如果最大值和最小值相等
        if (max == min) {
            return 0;
        }
        // 准备桶-只记录最大和最小
        // 当前桶有没有数据进来过
        boolean[] has = new boolean[len + 1];
        // 桶口
        int[] mins = new int[len + 1];
        // 桶底
        int[] maxs = new int[len + 1];

        // 当前遍历到的数，究竟去哪一个桶
        int bucketIndex = 0;
        // 桶初始化好了
        for (int i = 0; i < len; i++) {
            // 找桶号
            bucketIndex = bucket(arr[i], len, max, min);
            // 设置当前数和之前的数比较，较小就替换，没有就新增
            mins[bucketIndex] = has[bucketIndex] ? Math.min(mins[bucketIndex], arr[i]) : arr[i];
            // 设置当前数和之前的数比较，较大就替换，没有就新增
            maxs[bucketIndex] = has[bucketIndex] ? Math.max(maxs[bucketIndex], arr[i]) : arr[i];
            has[bucketIndex] = true;
        }
        // 找返回
        int ans = 0;
        int lastMax = maxs[0];
        // 为何从1开始？因为我们的桶内的最大和最小不会超过桶的实际区间大小
        for (int i = 1; i <= len; i++) {
            // 当前桶有数，就算，没数就跳过，上一个最大值不变，但是下一个最小值往后遍历
            if (has[i]) {
                ans = Math.max(ans, mins[i] - lastMax);
                lastMax = maxs[i];
            }
        }
        return ans;
    }

    public static int bucket(long num, long len, long max, long min) {
        return (int) ((num - min) * len / (max - min));
    }

    public static void main(String[] args) {
        int[] arr = {7, 13, 5, 12, 17, 8, 4, 10, 9};
        // 4,5,7,8,9,10,12,13,17

        System.out.println(maxGap1(arr));
        System.out.println(maxGap2(arr));
    }
}
