package custom.code_2022_10;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

/**
 * @ClassName LeetCode_950
 * @Author Duys
 * @Description
 * @Date 2022/10/19 15:54
 **/
// 950. 按递增顺序显示卡牌
public class LeetCode_950 {

    // 输入：[17,13,11,2,3,5,7]
    // 输出：[2,13,3,11,5,17,7]
    // 2 3 5 7 11 13 17
    // 17 13
    // 13 17 11
    // 17 11 13 7
    // 11 13 7 17 5
    // 13 7 17 5  11 3
    // 7 17 5 11 3 13 2
    // 逆推，
    // 1.队尾放大的
    // 2.队尾加入队头弹出的元素
    // 3.把排序后的元素加入队列
    // 4.重复上面的步骤
    public int[] deckRevealedIncreasing(int[] deck) {
        int n = deck.length;
        if (n == 1) {
            return deck;
        }
        Arrays.sort(deck);
        LinkedList<Integer> queue = new LinkedList<>();
        queue.addLast(deck[n - 1]);// 大的放入队尾
        for (int i = n - 2; i >= 0; i--) {
            queue.addLast(queue.pollFirst()); // 先把队头的放到队尾来
            queue.addLast(deck[i]); // 然后加入当前的
        }
        int[] ans = new int[n];
        for (int i = 0; i < n; i++) {
            ans[i] = queue.pollLast();
        }
        return ans;
    }
}
