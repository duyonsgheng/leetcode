package custom.code_2022_07;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @ClassName LeetCode_224
 * @Author Duys
 * @Description
 * @Date 2022/7/13 10:09
 **/
//224. 基本计算器
public class LeetCode_224 {

    public static int calculate(String s) {
        if (s == null) {
            return 0;
        }
        // 预处理，把 ' '去掉
        char[] chars = s.toCharArray();
        List<Character> characters = new ArrayList<>();
        for (char c : chars) {
            if (c != ' ') {
                characters.add(c);
            }
        }
        char[] chars1 = new char[characters.size()];
        int index = 0;
        for (Character c : characters) {
            chars1[index++] = c;
        }
        return process(chars1, 0)[0];
    }

    // 从str[i]开始往下计算，遇到终止位置或者右括号就停
    // 返回两个值，一个值当前计算的值，和计算的位置
    public static int[] process(char[] str, int i) {
        // 准备一个栈或者队列，把遇到的数字或者符号全部压入栈或者队列
        // 只计算  * / 运算。加减运算的压栈
        LinkedList<String> que = new LinkedList();
        int cur = 0;
        int[] ans = null;
        while (i < str.length && str[i] != ')') {
            if (str[i] >= '0' && str[i] <= '9') {
                cur = cur * 10 + str[i++] - '0';
            } else if (str[i] != '(') { // 运算符号了
                // 该计算了，只处理乘除
                addNum(que, cur);
                que.addLast(String.valueOf(str[i++]));
                // 遇到运算
                cur = 0;
            } else {
                ans = process(str, i + 1);
                cur = ans[0];
                i = ans[1] + 1;
            }
        }
        // 需要处理乘除
        addNum(que, cur);
        // 只计算简答的加减
        return new int[]{getNum(que), i};
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

    public static void main(String[] args) {
        String srt = "1 + 1";
        System.out.println(calculate(srt));
    }
}
