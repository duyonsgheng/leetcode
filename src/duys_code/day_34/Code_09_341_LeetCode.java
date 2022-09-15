package duys_code.day_34;

import java.util.Iterator;
import java.util.List;
import java.util.Stack;

/**
 * @ClassName Code_08_341_LeetCode
 * @Author Duys
 * @Description https://leetcode-cn.com/problems/flatten-nested-list-iterator/
 * @Date 2021/12/7 14:44
 **/
public class Code_09_341_LeetCode {

    public interface NestedInteger {

        // @return true if this NestedInteger holds a single integer, rather than a
        // nested list.
        public boolean isInteger();

        // @return the single integer that this NestedInteger holds, if it holds a
        // single integer
        // Return null if this NestedInteger holds a nested list
        public Integer getInteger();

        // @return the nested list that this NestedInteger holds, if it holds a nested
        // list
        // Return null if this NestedInteger holds a single integer
        public List<NestedInteger> getList();
    }

    public class NestedIterator implements Iterator<Integer> {

        private List<NestedInteger> list;
        private Stack<Integer> stack; // 存放的是List的下标
        private boolean used;

        public NestedIterator(List<NestedInteger> nestedList) {
            list = nestedList;
            stack = new Stack<>();
            stack.push(-1);
            used = true;
            hasNext();
        }

        @Override
        public boolean hasNext() {
            if (stack.isEmpty()) {
                return false;
            }
            if (!used) {
                return true;
            }
            if (findNext(list, stack)) {
                used = false;
            }
            return !used;
        }

        @Override
        public Integer next() {
            Integer ans = null;
            if (!used) {
                ans = get(list, stack);
                used = true;
                hasNext();
            }
            return ans;
        }

        private Integer get(List<NestedInteger> list, Stack<Integer> stack) {
            int index = stack.pop();
            Integer ans = null;
            if (!stack.isEmpty()) {
                ans = get(list.get(index).getList(), stack);
            } else {
                ans = list.get(index).getInteger();
            }
            stack.push(index);
            return ans;
        }

        private boolean findNext(List<NestedInteger> list, Stack<Integer> stack) {
            int index = stack.pop();
            if (!stack.isEmpty() && findNext(list.get(index).getList(), stack)) {
                stack.push(index);
                return true;
            }
            for (int i = index + 1; i < list.size(); i++) {
                if (pickFirst(list.get(i), i, stack)) {
                    return true;
                }
            }
            return false;
        }

        private boolean pickFirst(NestedInteger nested, int position, Stack<Integer> stack) {
            if (nested.isInteger()) {
                stack.add(position);
                return true;
            }
            List<NestedInteger> list = nested.getList();
            for (int i = 0; i < list.size(); i++) {
                if (pickFirst(list.get(i), i, stack)) {
                    stack.add(position);
                    return true;
                }
            }
            return false;
        }
    }
}
