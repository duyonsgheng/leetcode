package duys_code.day_08;

/**
 * @ClassName Code_02_MostWarter
 * @Author Duys
 * @Description 力扣: https://leetcode-cn.com/problems/container-with-most-water/submissions/
 * @Date 2021/10/11 15:05
 **/
public class Code_02_MostWater {
    /**
     * 问数组的每一个元素代表再X轴的一个高度，问最多的水量
     */
    /**
     * 思路：双指针问题，左边指针最开始再0位置，右边指针最开始再length-1位置
     * 每一次结算答案。谁小就移动谁，属于一个贪心
     */
    public static int maxArea(int[] height) {
        if (height == null || height.length < 1) {
            return 0;
        }
        int L = 0;
        int R = height.length - 1;
        int max = Integer.MIN_VALUE;
        while (L < R) {
            max = Math.max(Math.min(height[L], height[R]) * (R - L), max);
            if (height[L] > height[R]) {
                R--;
            } else {
                L++;
            }
        }
        return max;
    }
}
