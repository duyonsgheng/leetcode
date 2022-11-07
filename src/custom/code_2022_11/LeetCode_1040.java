package custom.code_2022_11;

import java.util.Arrays;

/**
 * @ClassName LeetCode_1040
 * @Author Duys
 * @Description
 * @Date 2022/11/7 15:50
 **/
// 1040. 移动石子直到连续 II
public class LeetCode_1040 {

    public int[] numMovesStonesII(int[] stones) {
        Arrays.sort(stones);
        int n = stones.length;
        // stones[l1,l2.....r2,r1]
        int l1 = stones[0], l2 = stones[1], r2 = stones[n - 2], r1 = stones[n - 1];
        if (r1 - l1 + 1 == n) {// 完整连续，没有移动的余地，n个数字连续
            return new int[]{0, 0};
        }
        int choice1 = r2 - l1 - n + 2;// 不含最右端r1时，[l1,r2]间空格的数量。(r2 - l1 + 1) - (n-1);// 数量 - 数字数量 = 空位置数量
        int choice2 = r1 - l2 - n + 2;// 不含最左端l1时
        int ans2 = Math.max(choice1, choice2);// 最大可能，最墨迹拿法，在最开始时，拿起一端，剩下数字之间空格，都可以被墨迹到
        if (choice1 == 0) {// r1 都连上了,[l1,r2]上连续一个不差
            if (r1 - r2 == 2) {// 最后两个数中间有一个空
                return new int[]{1, 1};// 最小，最左拿过来塞进去，最大也是拿最左，r1拿不起来
            } else {
                return new int[]{2, ans2};// 超过两个空格，最小从左端拿一个在放在r2+2位置，r1放在r2+1位置，2次
            }
        } else if (choice2 == 0) {
            if (l2 - l1 == 2) {//
                return new int[]{1, 1};
            } else {
                return new int[]{2, ans2};
            }
        } else if (choice1 == 1 || choice2 == 1) {
            // [l1,r2]中间差一个
            return new int[]{1, ans2};// 最少把单个的插进来。最多看空位了。总能拿起最一端往空格间插入
        } else {
            // 差别大于1
            int max = this.containsMax(stones, n);// n长窗口上最多容下了多少个数字
            int ans1 = n - max;// 所有的空都能被最小方式插入
            return new int[]{ans1, ans2};
        }
    }

    // 数字跨度size的情况下，最多能包含array上多少个数字
    private int containsMax(int[] array, int size) {
        int l = 0, r = 0, len = array.length;
        int ans = 0;
        while (r < len) {
            while (r < len && array[r] < array[l] + size) {
                r++;
            }
            ans = Math.max(ans, r - l);
            l++;
        }
        return ans;
    }
}
