package duys.class_06_08;

/**
 * @ClassName SmallList
 * @Author Duys
 * @Description 给定一个链表的头部，给定一个值，以给定值把链表分区域， <  =  > 三个区域
 * @Date 2021/6/9 15:46
 **/
public class SmallList {
    public static class Node {
        public int value;
        public Node next;

        public Node(int data) {
            this.value = data;
        }
    }

    public static Node listPartition(Node head, int tag) {
        // 申请几个变量
        Node sH = null; // 小于区域的head
        Node sT = null; // 小于区域的尾部
        Node eH = null;
        Node eT = null;
        Node mH = null;
        Node mT = null;
        // 因为实在原来链表上做出改动，需要每次记录
        Node next = null;

        while (head != null) {
            next = head.next;
            // 因为
            head.next = null;
            // 小于
            if (head.value < tag) {
                // 如果当前小于区域没有，那么直接头尾都指向自己
                if (sH == null) {
                    sH = head;
                    sT = head;
                }
                // 如果有了，那么把尾部的next指向自己
                // 把自己塞到尾部上去
                else {
                    sT.next = head;
                    sT = head;
                }
            }
            // 等于
            else if (head.value == tag) {
                if (eH == null) {
                    eH = head;
                    eT = head;
                } else {
                    eT.next = head;
                    eT = head;
                }
            }
            // 大于
            else {
                if (mH == null) {
                    mH = head;
                    mT = head;
                } else {
                    mT.next = head;
                    mT = head;
                }
            }
            head = next;
        }

        // 小于区域的尾部链接等于区域的头部，等于区域的尾巴链接大于区域的头
        // 如果有小于区域
        if (sT != null) {
            sT.next = eH;
            // 如果等于区域尾部为空，那么当前的链表尾部还是小于区域的尾部
            eT = eT == null ? sT : eT;
        }
        if (eT != null) {
            eT.next = mH;
        }
        // 上面两步做完了。小于区域和等于区域链接好了
        // 如果有小于区域
        if (sH != null) {
            return sH;
        } else {
            if (eH != null) {
                return eH;
            } else {
                return mH;
            }
        }
    }
}
