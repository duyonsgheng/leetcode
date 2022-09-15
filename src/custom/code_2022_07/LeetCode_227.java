package custom.code_2022_07;

import java.util.LinkedList;

/**
 * @ClassName LeetCode_227
 * @Author Duys
 * @Description
 * @Date 2022/7/13 10:47
 **/
// 227. 基本计算器 II
// 由整数和算符 ('+', '-', '*', '/') 组成，中间由一些空格隔开
public class LeetCode_227 {

    public static int calculate(String s) {
        if (s == null || s.length() < 1) {
            return 0;
        }
        LinkedList<String> que = new LinkedList<>();
        int i = 0;
        int cur = 0;
        char[] str = s.toCharArray();
        while (i < str.length) {
            if (str[i] == ' ') {
                i++;
                continue;
            }
            if (str[i] >= '0' && str[i] <= '9') {
                cur = cur * 10 + str[i++] - '0';
            } else {
                // 该计算了，只处理乘除
                addNum(que, cur);
                que.addLast(String.valueOf(str[i++]));
                // 遇到运算
                cur = 0;
            }
        }
        addNum(que, cur);
        return getNum(que);
    }

    public static int getNum(LinkedList<String> que) {
        int ans = 0;
        boolean isAdd = true;
        String cur = null;
        int num = 0;
        while (!que.isEmpty()) {
            cur = que.pollFirst();
            if (cur.equals("+")) {
                isAdd = true;
            } else if (cur.equals("-")) {
                isAdd = false;
            } else {
                num = Integer.valueOf(cur);
                ans += isAdd ? num : (-num);
            }
        }
        return ans;
    }

    public static void addNum(LinkedList<String> que, int num) {
        if (!que.isEmpty()) {
            int cur = 0;
            String top = que.pollLast();
            if (top.equals("+") || top.equals("-")) {
                que.addLast(top);
            } else {
                cur = Integer.valueOf(que.pollLast());
                num = top.equals("*") ? (num * cur) : (cur / num);
            }
        }
        que.addLast(String.valueOf(num));
    }

    public static void main(String[] args) {
        String str = "1 + 2 * 3 / 3 + 5";
        System.out.println(calculate(str));
    }
}
