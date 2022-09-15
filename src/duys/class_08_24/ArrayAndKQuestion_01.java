package duys.class_08_24;

/**
 * @ClassName ArrayAndKQuestion
 * @Author Duys
 * @Description
 * @Date 2021/8/24 16:49
 **/
public class ArrayAndKQuestion_01 {
    /**
     * 给定一个正整数组成的无序数组arr，给定一个正整数值K
     * 找到arr的所有子数组里，哪个子数组的累加和等于K，并且是长度最大的
     * 返回其长度
     */
    /**
     * 解法1：窗口滑动问题 [l...r]，开始的时候l=r =0,r++，然后看看下面的条件中了哪一个
     * 1.如果窗口内的累加和 <k r++
     * 2.如果窗口内的累加和 =k 收集答案 ，r++
     * 3.如果窗口内的累加和 >k l++
     * ---
     * 总结：窗口类问题，都存在一定的单调性，
     * 比如，一个非负数组的累加和随着数组的变长，累加和只会更大或者不变，随着数组长度减小，数组的累加和只会减小或者不变
     */

    public static int getMaxLength1(int[] arr, int k) {
        int l = 0, r = 0;
        int N = arr.length;
        int sum = arr[0];
        int ans = 0;
        while (r < N) {
            if (sum == k) {
                // 收集答案
                ans = Math.max(ans, r - l + 1);
                r++;
                if (r == arr.length) {
                    break;
                }
                sum += arr[r];
            } else if (sum < k) {
                r++;
                if (r == arr.length) {
                    break;
                }
                sum += arr[r];
            } else {
                sum -= arr[l++];
            }
        }
        return ans;
    }
}
