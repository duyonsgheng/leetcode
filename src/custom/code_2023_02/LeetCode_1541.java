package custom.code_2023_02;

/**
 * @ClassName LeetCode_1541
 * @Author Duys
 * @Description
 * @Date 2023/2/8 11:00
 **/
// 1541. 平衡括号字符串的最少插入次数
public class LeetCode_1541 {
    // ))())(
    public static int minInsertions(String s) {
        char[] arr = s.toCharArray();
        int left = 0;
        int ans = 0;
        int n = arr.length;
        // 任何左括号 '(' 必须对应两个连续的右括号 '))'
        for (int i = 0; i < n; i++) {
            if (arr[i] == '(') {
                left++;
            } else {
                // 如果有连续的
                if (i + 1 < n && arr[i + 1] == ')') {
                    i++;
                } else ans++; // 少了一个，需要加1个左括号
                if (left > 0) { // 如果当前是两个连续的 )) 那么消耗一个之前的left
                    left--;
                } else ans++; // 不然就少了一个 ( 来匹配当前的两个 ))
            }
        }
        return ans + left * 2;
    }

    public static void main(String[] args) {
        System.out.println(minInsertions("(()))"));
        System.out.println(minInsertions("))())("));
    }
}
