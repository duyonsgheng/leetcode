package week.week_2022_06_04;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @ClassName Code_02_StackManager
 * @Author Duys
 * @Description
 * @Date 2022/6/30 9:22
 **/
// 来自微软
// 请设计一种叫做“栈的管理器”的结构，实现如下6个功能
// 1) void createNewStack() : 可以在该结构中生成一个栈结构，编号从0开始
// 2) void push(int num, int stackIndex) : 将编号为stackIndex的栈里，压入num
// 3) int pop(int stackIndex) : 从编号为stackIndex的栈里，弹出栈顶返回
// 4) int peek(int stackIndex) ：从编号为stackIndex的栈里，返回栈顶但是不弹出
// 5) boolean isEmpty(int statckIndex)：返回编号为stackIndex的栈是否为空
// 6) int stackSize() : 返回一共生成了多少个栈
// 要求：不管用户调用多少次上面的方法，只使用有限几个动态数组(常数个)，完成代码实现
public class Code_02_StackManager {

    // 普通的解，没分
    public static class StackManage1 {
        private List<Stack<Integer>> stacks;

        public StackManage1() {
            stacks = new ArrayList<>();
        }

        public void createNewStack() {
            stacks.add(new Stack<>());
        }

        public int stackSize() {
            return stacks.size();
        }

        public void push(int num, int stackIndex) {
            stacks.get(stackIndex).push(num);
        }

        public int pop(int stackIndex) {
            return stacks.get(stackIndex).pop();
        }

        public int peek(int stackIndex) {
            return stacks.get(stackIndex).peek();
        }

        public boolean isEmpty(int statckIndex) {
            return stacks.get(statckIndex).isEmpty();
        }
    }

    // 要求：不管用户调用多少次上面的方法，只使用有限几个动态数组(常数个)，完成代码实现
    // 其实就是反向索引表 + 位置组成的一个链表，使用动态数组来实现
    public static class StackManage2 {

        public List<Integer> heads;  // 头在哪里
        public List<Integer> values; // 值数组
        public List<Integer> lasts;  // 当前位置的前一个在哪个位置上
        public List<Integer> frees;  // 哪些位置是空闲的
        public int size;
        public int freeSize;

        public StackManage2() {
            heads = new ArrayList<>();
            values = new ArrayList<>();
            lasts = new ArrayList<>();
            frees = new ArrayList<>();
            size = 0;
            freeSize = 0;
        }

        public void createNewStack() {
            heads.add(-1);
        }

        public int stackSize() {
            return heads.size();
        }

        public void push(int num, int stackIndex) {
            // 先把老的头部拉出来
            int oldHead = heads.get(stackIndex);
            if (freeSize == 0) {
                // 使用size 来保证新添加的值的位置
                heads.set(stackIndex, size++);
                values.add(num);// values中的index 和 size 是同步的
                lasts.add(oldHead);
            }
            // 有空闲的位置
            else {
                int freeIndex = frees.get(--freeSize);
                heads.set(stackIndex, freeIndex);
                values.set(freeIndex, num);
                // 更新之前的位置
                lasts.set(freeIndex, oldHead);
            }
        }

        public int pop(int stackIndex) {
            // 当前的头部
            int curIndex = heads.get(stackIndex);
            int ans = values.get(curIndex);
            int newHead = lasts.get(curIndex);
            heads.set(stackIndex, newHead);

            // 垃圾区
            // 如果当前垃圾区的大小和实际大小实际大小相等的
            if (freeSize >= frees.size()) {
                frees.add(curIndex);
                freeSize++;
            } else {
                frees.set(freeSize++, curIndex);
            }
            return ans;
        }

        public int peek(int stackIndex) {
            return values.get(heads.get(stackIndex));
        }

        public boolean isEmpty(int statckIndex) {
            return heads.get(statckIndex) == -1;
        }
    }
}
