package duys_code.day_36;

/**
 * @ClassName Code_04_ComputeExpressionValue
 * @Author Duys
 * @Description
 * @Date 2021/12/13 13:40
 **/
public class Code_04_ComputeExpressionValue {
    // 来自美团
    // () 分值为2
    // (()) 分值为3
    // ((())) 分值为4
    // 也就是说，每包裹一层，分数就是里面的分值+1
    // ()() 分值为2 * 2
    // (())() 分值为3 * 2
    // 也就是说，每连接一段，分数就是各部分相乘，以下是一个结合起来的例子
    // (()())()(()) -> (2 * 2 + 1) * 2 * 3 -> 30
    // 给定一个括号字符串str，已知str一定是正确的括号结合，不会有违规嵌套
    // 返回分数
    public static int sores(String s) {
        return compute(s.toCharArray(), 0)[0];
    }

    // 函数的意义
    // 当s从i开始直到遇到 ')' 或者终止符号就停
    // 返回当前的分数和处理到的位置
    public static int[] compute(char[] s, int i) {
        if (s[i] == ')') {
            return new int[]{1, i};
        }
        int ans = 1;
        while (i < s.length && s[i] != ')') {
            int[] next = compute(s, i + 1);
            ans *= next[0] + 1;
            i = next[1] + 1;
        }
        return new int[]{ans, i};
    }
}
