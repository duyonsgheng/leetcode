package week.week_2023_05_03;

/**
 * @ClassName Code_07_LeetCode_1703
 * @Author Duys
 * @Description
 * @Date 2023/5/18 10:05
 **/
// 1703. 得到连续 K 个 1 的最少相邻交换次数
// https://leetcode.cn/problems/minimum-adjacent-swaps-for-k-consecutive-ones/
public class Code_07_LeetCode_1703 {

    // 思路：窗口，而这个窗口不于传统的窗口一样，
    // 当前窗口大小是k，我们把窗口分为左右两半。左边的最近的 k/2个1 在什么位置，右边的 k/2个1在什么位置
    // 把左边最近的k/2个1移动到当前窗口的左边比如 abc位置需要代价 之前的最近k/2个1 在 1到5位置，那么就是 a+b+c -(1+2+3+4+5)
    // 右边也同理
    public int minMoves(int[] nums, int k) {
        if (k == 1) {
            return 0;
        }
        int n = nums.length;
        int x = (k - 1) / 2;
        int leftAimIndiesSum = x * (x + 1) / 2;
        int rightAimIndiesSum = (int) ((long) (k - 1) * k / 2 - leftAimIndiesSum);
        int ans = Integer.MAX_VALUE;
        int l = 0;
        int m = (k - 1) / 2;
        int r = k - 1;
        int leftNeedOnes = m + 1;
        int leftWindowL = 0;
        int leftWindowOnes = 0;
        int leftWindowOnesIndiesSum = 0;
        for (int i = 0; i < m; i++) {
            if (nums[i] == 1) {
                leftWindowOnes++;
                leftWindowOnesIndiesSum += i;
            }
        }
        int rightNeedOnes = k - leftNeedOnes;
        int rightWindowR = m;
        int rightWindowOnes = nums[m];
        int rightWindowOnesIndiesSum = nums[m] == 1 ? m : 0;
        for (; r < n; l++, m++, r++) {
            if (nums[m] == 1) {
                leftWindowOnes++;
                leftWindowOnesIndiesSum += m;
                rightWindowOnes--;
                rightWindowOnesIndiesSum -= m;
            }
            while (leftWindowOnes > leftNeedOnes) {
                if (nums[leftWindowL] == 1) {
                    leftWindowOnes--;
                    leftWindowOnesIndiesSum -= leftWindowL;
                }
                leftWindowL++;
            }
            while (rightWindowOnes < rightNeedOnes && rightWindowR + 1 < n) {
                if (nums[rightWindowR + 1] == 1) {
                    rightWindowOnes++;
                    rightWindowOnesIndiesSum += rightWindowR + 1;
                }
                rightWindowR++;
            }
            if (leftWindowOnes == leftNeedOnes && rightWindowOnes == rightNeedOnes) {
                ans = Math.min(ans,
                        leftAimIndiesSum - leftWindowOnesIndiesSum + rightWindowOnesIndiesSum - rightAimIndiesSum);
            }
            leftAimIndiesSum += m + 1 - l;
            rightAimIndiesSum += r - m;
        }
        return ans;
    }
}
