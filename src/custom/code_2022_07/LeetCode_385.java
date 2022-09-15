package custom.code_2022_07;

import duys_code.day_34.Code_09_341_LeetCode;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * @ClassName LeetCode_385
 * @Author Duys
 * @Description
 * @Date 2022/7/22 11:12
 **/
// 385. 迷你语法分析器
// 给定一个字符串 s 表示一个整数嵌套列表，实现一个解析它的语法分析器并返回解析的结果NestedInteger 。
//列表中的每个元素只可能是整数或整数嵌套列表
//链接：https://leetcode.cn/problems/mini-parser
public class LeetCode_385 {
    // 这就是嵌套递归问题
    // 可以使用栈来模拟
    public NestedInteger deserialize(String s) {
        NestedInteger nestedInteger = new NestedInteger(0);
        Deque<NestedInteger> queue = new LinkedList<>();
        char[] str = s.toCharArray();
        int n = str.length;
        int i = 0;
        while (i < n) {
            if (str[i] == ',' && i++ >= 0) {
                continue;
            }
            if (str[i] == '-' || (str[i] >= '0' && str[i] <= '9')) {
                int j = str[i] == '-' ? i + 1 : i;
                int num = 0;
                while (j < n && str[j] >= '0' && str[j] <= '9') {
                    num = num * 10 + str[j++] - '0';
                }
                queue.addLast(new NestedInteger(str[i] == '-' ? -num : num));
                i = j;
            } else if (str[i] == '[') {
                queue.addLast(new NestedInteger());
                queue.addLast(nestedInteger);
                i++;
            } else { // 右括号了
                List<NestedInteger> list = new ArrayList<>();
                while (!queue.isEmpty()) {
                    NestedInteger cur = queue.pollLast();
                    if (cur == nestedInteger) {
                        break;
                    }
                    list.add(cur);
                }
                for (int m = list.size() - 1; m >= 0; m--) {
                    queue.peekLast().add(list.get(m));
                }
                i++;
            }
        }
        return queue.peekLast();
    }

    public NestedInteger deserialize1(String s) {
        return process(s.toCharArray(), 0);
    }

    public static NestedInteger process(char[] str, int i) {

        if (str[i] == '[') {
            NestedInteger integer = new NestedInteger();
            while (str[i] != ']') {
                integer.add(process(str, i + 1));
                if (str[i] == ',') {
                    i++;
                }
            }
            i++;
            return integer;
        } else {
            boolean negative = false;
            if (str[i] == '-') {
                negative = true;
                i++;
            }
            int num = 0;
            while (i < str.length && str[i] >= '0' && str[i] <= '9') {
                num = num * 10 + str[i] - '0';
                i++;
            }
            if (negative) {
                num = num * -1;
            }
            return new NestedInteger(num);
        }

    }

    public static class Info {
        NestedInteger nestedInteger;
        int end;

        public Info(NestedInteger a, int e) {
            nestedInteger = a;
            end = e;
        }
    }

    public static class NestedInteger {
        // Constructor initializes an empty nested list.
        public NestedInteger() {
        }

        // Constructor initializes a single integer.
        public NestedInteger(int value) {
        }

        // @return true if this NestedInteger holds a single integer, rather than a nested list.
        public boolean isInteger() {
            return false;
        }

        // @return the single integer that this NestedInteger holds, if it holds a single integer
        // Return null if this NestedInteger holds a nested list
        public Integer getInteger() {
            return null;
        }

        // Set this NestedInteger to hold a single integer.
        public void setInteger(int value) {
        }

        // Set this NestedInteger to hold a nested list and adds a nested integer to it.
        public void add(NestedInteger ni) {
        }

        // @return the nested list that this NestedInteger holds, if it holds a nested list
        // Return empty list if this NestedInteger holds a single integer
        public List<NestedInteger> getList() {
            return null;
        }
    }
}
