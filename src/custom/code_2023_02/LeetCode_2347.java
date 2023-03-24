package custom.code_2023_02;

import java.util.Arrays;

/**
 * @ClassName LeetCode_2347
 * @Author Duys
 * @Description
 * @Date 2023/2/20 12:53
 **/
// 2347. 最好的扑克手牌
public class LeetCode_2347 {
    public static String bestHand(int[] ranks, char[] suits) {
        int[] sus = new int[5];
        Arrays.sort(ranks);
        for (int i = 0; i < 5; i++) {
            sus[suits[i] - 'a']++;
        }
        // 花色几种
        int color = 0;
        for (int i = 0; i < 5; i++) {
            color += sus[i] != 0 ? 1 : 0;
        }
        if (color == 1) {
            return "Flush";
        }
        int cnt = 1;
        int max = 1;
        for (int i = 1; i < 5; i++) {
            if (ranks[i] == ranks[i - 1]) {
                cnt++;
            } else {
                cnt = 1;
            }
            max = Math.max(max, cnt);
        }
        if (max == 1) {
            return "High Card";
        } else if (max == 2) {
            return "Pair";
        } else {
            return "Three of a Kind";
        }
    }

    public static void main(String[] args) {
        System.out.println(bestHand(new int[]{4, 4, 2, 4, 4}, new char[]{'d', 'a', 'a', 'b', 'c'}));
    }
}
