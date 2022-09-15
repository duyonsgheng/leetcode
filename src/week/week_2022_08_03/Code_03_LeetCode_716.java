package week.week_2022_08_03;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName Code_03_LeetCode_716
 * @Author Duys
 * @Description
 * @Date 2022/8/18 15:01
 **/
// 设计一个最大栈数据结构，既支持栈操作，又支持查找栈中最大元素。
// 实现MaxStack类：
// MaxStack()初始化栈对象
// void push(int x)将元素 x 压入栈中。
// int pop()移除栈顶元素并返回这个元素。
// int top()返回栈顶元素，无需移除。
// int peekMax()检索并返回栈中最大元素，无需移除。
// int popMax()检索并返回栈中最大元素，并将其移除。
// 如果有多个最大元素，只要移除 最靠近栈顶 的那个。
// 测试链接 : https://leetcode.cn/problems/max-stack/
public class Code_03_LeetCode_716 {
    // 使用加强堆
    class MaxStack {
        public int cnt;
        public HeapGreater<Node> heap;
        public Node top;

        public MaxStack() {
            cnt = 0;
            heap = new HeapGreater<>(new NodeComparator());
            top = null;
        }

        public void push(int x) {
            Node cur = new Node(x, ++cnt);
            heap.push(cur);
            if (top == null) {
                top = cur;
            } else {
                // 更新好
                top.last = cur;
                cur.next = top;
                top = cur;
            }
        }

        public int pop() {
            Node ans = top;
            if (top.next == null) {
                top = null;
            } else {
                top = top.next;
                top.last = null;
            }
            heap.remove(ans);
            return ans.val;
        }

        public int top() {
            return top.val;
        }

        public int peekMax() {
            return heap.peek().val;
        }

        public int popMax() {
            Node ans = heap.pop();
            if (ans == top) {
                if (top.next == null) {
                    top = null;
                } else {
                    top = top.next;
                    top.last = null;
                }
            } else {
                if (ans.next != null) {
                    ans.next.last = ans.last;
                }
                if (ans.last != null) {
                    ans.last.next = ans.next;
                }
            }
            return ans.val;
        }

        class NodeComparator implements Comparator<Node> {

            // 大根堆
            @Override
            public int compare(Node o1, Node o2) {
                return o1.val != o2.val ? (o2.val - o1.val) : (o2.cnt - o1.cnt);
            }

        }

        class Node {
            public int val;
            public int cnt;
            public Node next;
            public Node last;

            public Node(int v, int c) {
                val = v;
                cnt = c;
            }
        }

        class HeapGreater<T> {
            // 数据使用动态数组 来构建一颗二叉树
            private List<T> heap;
            // 反向索引
            private Map<T, Integer> indexMap;
            private int heapSize;
            // 比较器
            private Comparator<? super T> comp;

            public HeapGreater(Comparator<? super T> c) {
                comp = c;
                heap = new ArrayList<>();
                indexMap = new HashMap<>();
                heapSize = 0;
            }

            public T peek() {
                return heap.get(0);
            }

            public void push(T obj) {
                // 加到最后一个位置
                heap.add(obj);
                indexMap.put(obj, heapSize);
                heapInsert(heapSize++);
            }

            public T pop() {
                T ans = heap.get(0);
                // 把0位置和最后一个交换
                swap(0, heapSize - 1);
                indexMap.remove(ans);
                heap.remove(--heapSize);
                // 需要来一次向下的调整
                heapify(0);
                return ans;
            }

            public void remove(T obj) {
                T replace = heap.get(heapSize - 1);
                int index = indexMap.get(obj);
                indexMap.remove(obj);
                heap.remove(--heapSize);
                if (obj == replace) {
                    return;
                }
                // 替换掉原来位置的对象
                heap.set(index, replace);
                indexMap.put(replace, index);
                // 调整好堆结构
                resign(replace);
            }

            private void resign(T obj) {
                heapInsert(indexMap.get(obj));
                heapify(indexMap.get(obj));
            }

            // 往上找一个合适的位置，插入的时候，一般是把要插入的数，插入到最后一个节点，然后去调整
            private void heapInsert(int index) {
                while (comp.compare(heap.get(index), heap.get((index - 1) / 2)) < 0) {
                    swap(index, (index - 1) / 2);
                    index = (index - 1) / 2;
                }
            }

            // 往下调整 根据传进来的比较器，确定是大根堆还是小根堆
            private void heapify(int index) {
                int left = index * 2 + 1;
                while (left < heapSize) {
                    // 向下找自己较大/或者较小的孩子节点
                    int best = left + 1 < heapSize && comp.compare(heap.get(left + 1), heap.get(left)) < 0 ? left + 1 : left;
                    // 如果index大于了较大/较小孩子节点，那么就不用动了
                    best = comp.compare(heap.get(best), heap.get(index)) < 0 ? best : index;
                    if (best == index) {
                        break;
                    }
                    swap(best, index);
                    index = best;
                    left = index * 2 + 1;
                }
            }

            private void swap(int i, int j) {
                T o1 = heap.get(i);
                T o2 = heap.get(j);
                heap.set(i, o2);
                heap.set(j, o1);
                indexMap.put(o1, j);
                indexMap.put(o2, i);
            }
        }
    }


    public static void main(String[] args) {
        int a = 1;
        System.out.println(a++);
        System.out.println(a);
    }
}
