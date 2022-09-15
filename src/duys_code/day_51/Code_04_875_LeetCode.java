package duys_code.day_51;

/**
 * @ClassName Code_04_875_LeetCode
 * @Author Duys
 * @Description 力扣: https://leetcode-cn.com/problems/koko-eating-bananas/
 * @Date 2021/11/8 13:48
 **/
public class Code_04_875_LeetCode {
    /**
     * 珂珂喜欢吃香蕉。这里有N堆香蕉，第 i 堆中有piles[i]根香蕉。警卫已经离开了，将在H小时后回来。
     * 珂珂可以决定她吃香蕉的速度K（单位：根/小时）。每个小时，她将会选择一堆香蕉，从中吃掉 K 根。如果这堆香蕉少于 K 根，她将吃掉这堆的所有香蕉，然后这一小时内不会再吃更多的香蕉。
     * 珂珂喜欢慢慢吃，但仍然想在警卫回来前吃掉所有的香蕉。
     * 返回她可以在 H 小时内吃掉所有香蕉的最小速度 K（K 为整数）。
     */
    /**
     * 首先他的速度最大的是啥.就是数组中香蕉数量最多的哪一堆香蕉.但是因为喜欢慢慢吃.所以我们的答案是1~max 中的一个.此时我们开始进行二分
     */
    public int minEatingSpeed(int[] piles, int h) {
        if (piles == null || h <= 0) {
            return 0;
        }
        int L = 1;
        int R = 0;
        for (int p : piles) {
            R = Math.max(p, R);
        }
        int ans = 0;
        int M = 0;
        while (L <= R) {
            M = L + ((R - L) >> 1);
            if (check(piles, M) <= h) {
                ans = M;
                R = M - 1;
            } else {
                L = M + 1;
            }
        }
        return ans;
    }

    public int check(int[] ps, int sp) {
        int ans = 0;
        for (int p : ps) {
            // 向上取整
            ans += (p + sp - 1) / sp;
        }
        return ans;
    }
}
