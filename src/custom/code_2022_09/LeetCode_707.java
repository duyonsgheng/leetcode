package custom.code_2022_09;


import java.util.LinkedList;

/**
 * @ClassName LeetCode_707
 * @Author Duys
 * @Description
 * @Date 2022/9/13 13:09
 **/
// 707. 设计链表
public class LeetCode_707 {
    class MyLinkedList {

        private LinkedList<Integer> list;

        public MyLinkedList() {
            list = new LinkedList<>();
        }

        public int get(int index) {
            if (list.isEmpty() || list.size() <= index) {
                return -1;
            }
            return list.get(index);
        }

        public void addAtHead(int val) {
            list.addFirst(val);
        }

        public void addAtTail(int val) {
            list.addLast(val);
        }

        public void addAtIndex(int index, int val) {
            if (index == list.size()) {
                addAtTail(val);
            } else if (index > list.size()) {
                return;
            } else if (index < 0) {
                addAtHead(val);
            } else
                list.add(index, val);
        }

        public void deleteAtIndex(int index) {
            if (index < 0 || list.isEmpty() || index >= list.size()) {
                return;
            }
            list.remove(index);
        }
    }
}
