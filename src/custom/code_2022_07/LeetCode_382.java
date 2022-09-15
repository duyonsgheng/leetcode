package custom.code_2022_07;

import custom.base.ListNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @ClassName LeetCode_382
 * @Author Duys
 * @Description
 * @Date 2022/7/22 10:47
 **/
// 382. 链表随机节点
// 给你一个单链表，随机选择链表的一个节点，并返回相应的节点值。每个节点 被选中的概率一样 。
public class LeetCode_382 {
    class Solution {
        List<Integer> list;
        Random random = new Random(10000);

        public Solution(ListNode head) {
            list = new ArrayList<>();
            ListNode cur = head;
            while (cur != null) {
                list.add(cur.val);
                cur = cur.next;
            }
        }

        public int getRandom() {
            return list.get(random.nextInt(list.size()));
        }
    }
}
