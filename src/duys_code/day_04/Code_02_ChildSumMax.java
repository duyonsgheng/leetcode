package duys_code.day_04;

/**
 * @ClassName Code_02_ChildSumMax
 * @Author Duys
 * @Description 力扣：https://leetcode-cn.com/problems/maximum-subarray/
 * @Date 2021/9/22 14:55
 **/
public class Code_02_ChildSumMax {

    /**
     * 题意：
     * 返回一个数组中，子数组最大累加和
     * 范围尝试：以什么位置结尾的时候，往左边扩多大，累加和最大，预处理结构式一个前缀和数组
     * 1.i位置决定向左边扩展，向左边扩展的话，那么i位置一定最好的情况就是i-1最左的位置。
     * 2.i位置决定不向左边扩展，
     * 使用一个dp数组 ，一维的，把每一个位置最大的累加和都求出来，那么答案就在dp中的最大
     * 可以用一个pre代替dp数组。前一个位置的答案记录下来
     */

    public static int max(int[] arr) {
        if (arr == null || arr.length < 1) {
            return 0;
        }
        int ans = arr[0];
        int pre = arr[0];
        for (int i = 1; i < arr.length; i++) {
            pre = Math.max(arr[i], arr[i] + pre);
            ans = Math.max(pre, ans);
        }
        return ans;
    }

}
