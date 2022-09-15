package duys_code.day_23;

/**
 * @ClassName Code_01_LeftAndRightMax
 * @Author Duys
 * @Description
 * @Date 2021/11/11 15:04
 **/
public class Code_01_LeftAndRightMax {
    /**
     * 给定一个数组arr，长度为N > 1
     * 从中间切一刀，保证左部分和右部分都有数字，一共有N-1种切法
     * 如此多的切法中，每一种都有:
     * 绝对值(左部分最大值 – 右部分最大值)
     * 返回最大的绝对值是多少
     */
    // 绝对的贪心
    // 1.假设我们把数组的最大值划分到了左边，那么我们左边的最大值减去右边的最大值怎么能尽可能的大，leftMax - rightMax ，
    // 又因为数据的全局最大值在左边，所以我们希望rightMax尽可能的小，当右边只有一个数的时候累加和最小
    // 2.假设我们把数组的最大值划分到数组的右边，那么就是rightMax-leftMax尽可能的大，就希望我们左边尽可能的小，当左边只有一个数的时候，累加和最小
    public static int maxABS3(int[] arr) {
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; i++) {
            max = Math.max(arr[i], max);
        }
        return max - Math.min(arr[0], arr[arr.length - 1]);
    }
}
