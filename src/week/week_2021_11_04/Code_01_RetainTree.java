package week.week_2021_11_04;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName Code_01_RetainTree
 * @Author Duys
 * @Description
 * @Date 2022/1/25 16:50
 **/

// 给定一棵树的头节点head
// 请按照题意，保留节点，没有保留的节点删掉
// 树调整完之后，返回头节点
public class Code_01_RetainTree {

    public static class Node {
        public int value;
        public boolean retain;
        public List<Node> nexts;

        public Node(int v, boolean r) {
            value = v;
            retain = r;
            nexts = new ArrayList<>();
        }
    }

    public static Node retain(Node head) {
        // 是否有下级
        if (head.nexts.isEmpty()) {
            return head.retain ? head : null;
        }
        // head有下层节点
        List<Node> newNexts = new ArrayList<>();
        for (Node next : head.nexts) {
            Node newNext = retain(next);
            if (newNext != null) {
                newNexts.add(newNext);
            }
        }
        // head.nexts 是老的链表
        // newNexts 是新的链表
        if (!newNexts.isEmpty() || head.retain) {
            head.nexts = newNexts;
            return head;
        }
        return null;
    }

    public static void prePrint(Node head) {
        System.out.println(head.value);
        for (Node next : head.nexts) {
            prePrint(next);
        }
    }
}
