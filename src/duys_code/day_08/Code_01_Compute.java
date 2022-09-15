package duys_code.day_08;


import java.util.LinkedList;

/**
 * @ClassName Code_01_Compute
 * @Author Duys
 * @Description 力扣：https://leetcode-cn.com/problems/basic-calculator-iii/
 * @Date 2021/10/11 10:10
 **/
public class Code_01_Compute {
    /**
     * 给定一个字符串str，str表示一个公式，
     * 公式里可能有整数、加减乘除符号和左右括号
     * 返回公式的计算结果，难点在于括号可能嵌套很多层
     * str="48*((70-65)-43)+8*1"，返回-1816。
     * str="3+1*4"，返回7。
     * str="3+(1*4)"，返回7。
     * 【说明】
     * 1.可以认为给定的字符串一定是正确的公式，即不需要对str做公式有效性检查
     * 2.如果是负数，就需要用括号括起来，比如“4*(-3)”但如果负数作为公式的开头或括号部分的开头，则可以没有括号，比如"-3*4"和"(-3*4)"都是合法的。
     * 3.不用考虑计算过程中会发生溢出的情况。
     */

    /**
     * 思考一个问题：如果没有括号的一个运算，很简单，数字和符号都压栈，数字压栈之前，检查一下当前是不是 * / 运算，如果是，就先运算，然后计算结果压栈
     * 所以我们就需要把一个符号的计算公式变成简单的，这里使用递归来，遇到左括号就进行递归，遇到右括号或者字符终止就停
     */
    public static int calculator(String str) {
        return process(str.toCharArray(), 0)[0];
    }

    // 从str[i] 开始往下算，遇到字符的终止位置或者右括号就停，
    // 返回两个值(一个长度为2的数组) arr[0]表示当前计算过程的答案 arr[1]表示当前计算过程算到什么位置了
    //
    public static int[] process(char[] str, int i) {
        // 准备一个栈或者队列，把遇到的数字或者符号全部压入栈或者队列
        // 1. 准备一个int cur变量来计算数字
        // 2. 遇到符号就把数组压栈，cur清零，遇到 * / 就计算，然后把计算结果压栈，保证栈里只有简单的 加减运算
        LinkedList<String> que = new LinkedList();
        int cur = 0;
        int[] ans = null;
        // 从i开始
        while (i < str.length && str[i] != ')') {
            // 如果是数字
            if (str[i] >= '0' && str[i] <= '9') {
                // 这里使用的asci码
                cur = cur * 10 + str[i++] - '0';
            }
            //如果没有遇到左括号，上面数字也不是，跟不可能是右括号，只能是运算符了
            else if (str[i] != '(') {
                // 这里遇到的是运算符，如果只是 加减就不用计算，直接压栈。如果是乘除运算，就需要先运算，再压栈
                // 数字压栈了，
                addNum(que, cur);
                // 符号是不是也要压栈呢
                que.addLast(String.valueOf(str[i++]));
                // 遇到运算符，cur清零
                cur = 0;
            }
            // 只能是左括号了，那么就进行递归了
            else {
                // 当遇到左括号了，那么进入下一个迭代了，让我的子过程去算
                ans = process(str, i + 1);
                cur = ans[0];
                // 这里是算到哪个位置停下的，可能是字符终止位置，也可能是右括号的，这里+1，表示下一个过程从右括号的下一个位置算起走
                i = ans[1] + 1;
            }
        }
        // 这里需要把最后一个字符也算上
        addNum(que, cur);
        return new int[]{getNum(que), i};
    }

    private static int getNum(LinkedList<String> que) {
        int res = 0;
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
                res += isAdd ? num : (-num);
            }
        }
        return res;
    }

    public static void addNum(LinkedList<String> que, int num) {
        if (!que.isEmpty()) {
            int cur = 0;
            String top = que.pollLast();
            if (top.equals("+") || top.equals("-")) {
                que.addLast(top);
            } else {
                // 把乘除之前的数字拿出来
                cur = Integer.valueOf(que.pollLast());
                num = top.equals("*") ? (num * cur) : (cur / num);
            }
        }
        // 无论如何需要压栈
        que.addLast(String.valueOf(num));
    }

    public static void main(String[] args) {
        String s = "-3+2*(12-3*(2+3))-3*(8/4)";
        System.out.println(calculator(s));
        String s1 = "abc3{cg2{df}}op";
        System.out.println(toStr(s1));
    }

    /**
     * 再来一个变形的比如 3{abc3{fjc2{gk}}} -> abcfjcgkgkfjcgkgkfjcgkgkabcfjcgkgkfjcgkgkfjcgkgkabcfjcgkgkfjcgkgkfjcgkgk
     */
    public static String toStr(String str) {
        return process1(str.toCharArray(), 0, 1).str;
    }

    public static Info process1(char[] str, int i, int pre) {
        LinkedList<InfoTo> que = new LinkedList<>();
        int cur = 0;
        String curS = "";
        Info info = null;
        while (i < str.length && str[i] != '}') {
            // 如果遇到的是数字
            if (str[i] >= '0' && str[i] <= '9') {
                // 把之前的搞进去
                que.addLast(new InfoTo(pre, curS));
                cur = cur * 10 + str[i++] - '0';
            } else if (str[i] != '{') {
                curS += str[i++];
            } else {
                info = process1(str, i + 1, cur == 0 ? 1 : cur);
                curS = info.str;
                i = info.i + 1;
            }
        }
        if (!que.isEmpty()) {
            InfoTo infoTo = que.pollLast();
            infoTo.str = infoTo.str + curS;
            que.addLast(infoTo);
        } else {
            que.addLast(new InfoTo(pre, curS));
        }
        //
        curS = getStrs(que);
        return new Info(curS, i);
    }

    public static String getStrs(LinkedList<InfoTo> que) {
        String res = "";
        while (!que.isEmpty()) {
            InfoTo infoTo = que.pollLast();
            for (int i = 0; i < infoTo.num; i++) {
                res += infoTo.str;
            }
        }
        return res;
    }

    public static class Info {
        public String str;
        public int i;

        public Info(String s, int n) {
            i = n;
            str = s;
        }
    }

    public static class InfoTo {
        public int num;
        public String str;

        public InfoTo(int n, String s) {
            num = n;
            str = s;
        }
    }

}
