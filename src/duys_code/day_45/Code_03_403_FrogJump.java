package duys_code.day_45;

import java.util.HashMap;
import java.util.HashSet;

/**
 * @ClassName Code_03_403_FrogJump
 * @Author Duys
 * @Description 力扣：https://leetcode-cn.com/problems/frog-jump/
 * @Date 2021/10/13 13:16
 **/
public class Code_03_403_FrogJump {
    /**
     * 青蛙过河问题
     * 青蛙可以跳 n-1 n n+1
     * 青蛙只能向前跳跃
     */
    public static boolean canCross(int[] stones) {
        if (stones == null || stones.length < 1) {
            return false;
        }
        HashSet<Integer> set = new HashSet<>();
        for (int s : stones) {
            set.add(s);
        }
        HashMap<Integer, HashMap<Integer, Boolean>> dp = new HashMap<>();
        // 题意给了，开始已经在1了，之前的也是1
        return jump(1, 1, stones[stones.length - 1], set, dp);
    }

    public static boolean jump(int cur, int pre, int end, HashSet<Integer> set, HashMap<Integer, HashMap<Integer, Boolean>> dp) {
        if (cur == end) {
            return true;
        }
        // 青蛙跳跃的目标位置不存在，也不行
        if (!set.contains(cur)) {
            return false;
        }
        if (dp.containsKey(cur) && dp.get(cur).containsKey(pre)) {
            return dp.get(cur).get(pre);
        }
        boolean ans = (pre > 1 && jump(cur + pre - 1, pre - 1, end, set, dp))
                || jump(cur + pre, pre, end, set, dp)
                || jump(cur + pre + 1, pre + 1, end, set, dp);
        if (!dp.containsKey(cur)) {
            dp.put(cur, new HashMap<>());
        }
        if (!dp.get(cur).containsKey(pre)) {
            dp.get(cur).put(pre, ans);
        }
        return ans;
    }
}
